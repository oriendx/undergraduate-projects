import socket, json, os, time, struct, hashlib
import rsa
import pickle
from errorclass import AuthenticationError
from cryptography.fernet import Fernet
import zlib

class Tcpip_Client():
    def __init__(self,encryption = "no"):
        self.file_change_client = dict()
        self.update_file_change("./share/")#
        self.fmt = "128si"
        self.send_buffer = 4096
        self.encryption = encryption

    def get_file_md5(self, filename):
        s = filename + "___"
        file = filename.split("/")[-1]
        f_path = s.split(file + "___")[0]
        full_file_path = os.path.join(f_path, file)
        m = hashlib.md5()
        file_size = '{:.2f}'.format(os.path.getsize(full_file_path) / (1024 ** 2))
        print('Verifying file name：%s， filesize：%s Mb' % (file, file_size))
        with open(full_file_path, 'rb') as f:
            while True:
                data = f.read(99999999)
                print('speed：%.2f Mb/s' % (len(data) / (1024 ** 2)), end='\r')
                if not data:
                    break
                m.update(data)
        file_md5 = m.hexdigest().upper()
        with open(full_file_path + ".md5", "wb") as md5_file:
            md5_file.write(file_md5.encode())
        md5_file.close()
        return file_md5

    def update_file_change(self, path):
        
        for f in os.listdir(path):
            full_path = path + f
            if os.path.isfile(full_path) and not full_path.endswith(".md5"):

                filesize = os.path.getsize(full_path)
                if full_path not in self.file_change_client.keys():
                    self.file_change_client[full_path] = filesize
                    change = True
                else:
                    filesize_old = self.file_change_client[full_path]
                    if filesize_old == filesize:
                        change = False
                    else:
                        self.file_change_client[full_path] = filesize
                        change = True

            elif not full_path.endswith(".md5"):
                change = self.update_file_change(full_path + "/")

        try:
            if change:
                with open("file_change_client.json", "w") as f:
                    json.dump(self.file_change_client, f)
                f.close()

        except:
            pass

    def send_new(self,bytes_sendData,client_socket,f):
        bytes_sendData = zlib.compress(bytes_sendData, 8)
        if self.encryption.upper() == "YES":

            en_sendData = f.enctrypt(bytes_sendData)
            client_socket.send(en_sendData)
        else:
            client_socket.send(bytes_sendData)
    def recv_new(self,client_socket,f):
        bytes_secvData = client_socket.recv(self.send_buffer)
        if self.encryption.upper() == "YES":
            recvData = f.decrypt(bytes_secvData)
        else:
            recvData = bytes_secvData
        recvData = zlib.decompress(recvData)
        return recvData

    def connect(self, ip_address, port):
        
        tcp_client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        tcp_client_socket.connect((ip_address, port))
        return tcp_client_socket

    def sync_share(self, ip_address, port):
        tcp_client_socket = self.connect(ip_address, port)
        f = None
        if self.encryption.upper()== "YES":
            self.asyKey = rsa.newkeys(2048)
          
            self.publicKey = self.asyKey[0]
            self.privateKey = self.asyKey[1]
            print("swnding public key to sercer")
            sendKey = pickle.dumps(self.publicKey)
            sendKeySha256 = hashlib.sha256(sendKey).hexdigest()
            tcp_client_socket.send(pickle.dumps((sendKey, sendKeySha256)))
            print("waiting for secure key")
           
            symKey, symKeySha256 = pickle.loads(tcp_client_socket.recv(self.send_buffer))
            if hashlib.sha256(symKey).hexdigest() != symKeySha256:
                raise AuthenticationError("key changed！")
            else:
                self.symKey = pickle.loads(rsa.decrypt(symKey, self.privateKey))
                print("key exchange finished")
            f = Fernet(self.symKey)

        while True:
            try:
                self.file_change_client = dict()
                self.update_file_change("./share/")
                local_file_change = self.file_change_client

                for filename in local_file_change.keys():
                    filesise = local_file_change[filename]
                    head = struct.pack(self.fmt, filename.encode(), filesise)
                    print("\nhead size:" + str(head.__len__()))
                    
                    self.send_new(head,tcp_client_socket,f)
                    re = self.recv_new(tcp_client_socket,f)
                    if re.decode().find("prepare to") >= 0:
                        restSize = filesise
                        md5_value = self.get_file_md5(filename)
                        fd = open(filename, 'rb')
                        count = 0
                        while restSize >= self.send_buffer:
                            data = fd.read(self.send_buffer)
                            self.send_new(data,tcp_client_socket,f)
                            restSize = restSize - self.send_buffer

                            count = count + 1
                        data = fd.read(restSize)
                        self.send_new(data, tcp_client_socket, f)
                        fd.close()

                        re = self.recv_new(tcp_client_socket,f)
                        if re.decode().find("prepare to download " + filename + ".md5") >= 0:
                            md5_filesize = os.path.getsize(filename + ".md5")
                            restSize = md5_filesize
                            md5_head = struct.pack(self.fmt, (filename + ".md5").encode(), md5_filesize)
                            print("\nmd5 head size:" + str(md5_head.__len__()))
                            self.send_new(md5_head,tcp_client_socket,f)
                            fd = open(filename + ".md5", 'rb')
                            count = 0
                            while restSize >= self.send_buffer:
                                data = fd.read(self.send_buffer)
                                self.send_new(data,tcp_client_socket,f)
                                restSize = restSize - self.send_buffer
                                count = count + 1
                            data = fd.read(restSize)
                            self.send_new(data, tcp_client_socket, f)
                            fd.close()
                        while True:
                            re = self.recv_new(tcp_client_socket,f)

                            if re.decode().find("download " + filename) >= 0:
                                break
                            else:
                                pass
                        if re.decode().find("success") >= 0:
                            print(filename, "successfully sent")
                        else:
                            print(filename, "failed to sent")
                    else:
                        print(filename, "dont't need to update")
            except:
                try:
                    tcp_client_socket = self.connect(ip_address, port)
                    if self.encryption.upper() == "YES":
                        self.asyKey = rsa.newkeys(2048)
                        
                        self.publicKey = self.asyKey[0]
                        self.privateKey = self.asyKey[1]
                        print("sending public key to server")
                        sendKey = pickle.dumps(self.publicKey)
                        sendKeySha256 = hashlib.sha256(sendKey).hexdigest()
                        tcp_client_socket.send(pickle.dumps((sendKey, sendKeySha256)))

                        
                        symKey, symKeySha256 = pickle.loads(tcp_client_socket.recv(1024))
                        if hashlib.sha256(symKey).hexdigest() != symKeySha256:
                            raise AuthenticationError("secure key chaged!")
                        else:
                            self.symKey = pickle.loads(rsa.decrypt(symKey, self.privateKey))
                            print("key exchange finished")
                        f = Fernet(self.symKey)
                except Exception as e:
                    print("connection Err:", e)

        self.tcp_client_socket.close()


if __name__ == "__main__":
    Tcpip_client = Tcpip_Client("no")

   
    Tcpip_client.sync_share("127.0.0.1", 20001)
