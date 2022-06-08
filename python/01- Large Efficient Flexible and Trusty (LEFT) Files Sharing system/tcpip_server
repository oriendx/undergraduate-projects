import socket,os,json,struct,time,hashlib,threading
from cryptography.fernet import Fernet
import pickle,rsa
from errorclass import AuthenticationError
import zlib

class Tcpip_Server():
    def __init__(self,port,encryption = "no"):
        self.file_change = dict()
        self.fmt = "128si"
        self.recv_buffer = 4096
        self.host = ""
        self.port = port
        self.tcp_server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.tcp_server_socket.bind((self.host, self.port))
        self.tcp_server_socket.listen(5)
        self.encryption = encryption

    def download_file(self,filename,filesize,client_socket,f):

        recved_size = 0
        s = filename+"_temp"
        a = s.split("/")[-1]
        filedir = s.split(a)[0]
        if not os.path.exists(filedir):
            os.makedirs(filedir)
            print("create filedir %s"%filedir)
        fd = open(filename, 'wb')
        count = 0

        while True:
            data = self.recv_new(client_socket,f)
            recved_size = recved_size + len(data)  
            fd.write(data)
            if recved_size == filesize:
                break
        fd.close()
        print("update file %s"%filename)
    def send_new(self,bytes_sendData,client_socket,f):
        bytes_sendData = zlib.compress(bytes_sendData, 8)
        if self.encryption.upper == "YES":
            en_sendData = f.enctrypt(bytes_sendData)
            client_socket.send(en_sendData)
        else:
            client_socket.send(bytes_sendData)
    def recv_new(self,client_socket,f):
        bytes_secvData = client_socket.recv(self.recv_buffer)
        if self.encryption.upper == "YES":
            recvData = f.decrypt(bytes_secvData)
        else:
            recvData = bytes_secvData
        recvData = zlib.decompress(recvData)
        return recvData
    def check_localfile(self,path,filename):
        filexist = False
        for f in os.listdir(path):
            full_path = path + f
            if os.path.isfile(full_path) and full_path == filename:
                filesize = os.path.getsize(full_path)
                return full_path,filesize
            elif os.path.isdir(full_path) and filename.find(full_path)>=0:
                return self.check_localfile(full_path+"/",filename)
        return filexist,0

    def check_md5_correct(self,filename):
        local_md5_value = self.get_file_md5(filename)
        with open(filename+".md5") as f:
            remote_md5_value = f.read()
        f.close()
        if remote_md5_value == local_md5_value:
            return True
        else:
            return False
    def get_file_md5(self,filename):
        s = filename+"___"
        file = filename.split("/")[-1]
        f_path = s.split(file+"___")[0]
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
        return file_md5
    def start(self):
        f = None
        client_socket, client_ip = self.tcp_server_socket.accept()
        print("Client:", client_ip, "connected")
        if self.encryption.upper()== "YES":
            a = client_socket.recv(1024)

            publicKeyPK, pubKeySha256 = pickle.loads(a)
            if hashlib.sha256(publicKeyPK).hexdigest() != pubKeySha256:
                raise AuthenticationError("secure key changed！")
            else:
                publicKey = pickle.loads(publicKeyPK)
                print("public key accept")
           
            sym_key = Fernet.generate_key()
            
            en_sym_key = rsa.encrypt(pickle.dumps(sym_key), publicKey)
            en_sym_key_sha256 = hashlib.sha256(en_sym_key).hexdigest()
            print("sengding secure key")
            client_socket.send(pickle.dumps((en_sym_key, en_sym_key_sha256)))
            f = Fernet(sym_key)
        headsize = struct.calcsize(self.fmt)
        while True:
            try:
                head = self.recv_new(client_socket,f)
                filename = struct.unpack(self.fmt, head)[0].decode().rstrip('\0') 
                filesize = struct.unpack(self.fmt, head)[1]

                print("filename:" + filename + "\nfilesize:" + str(filesize))
                localfile_name, local_filesize = self.check_localfile("./share/", filename)

                if localfile_name== False:
                    self.send_new(("prepare to download "+filename).encode(),client_socket,f = f)
                    self.download_file(filename,filesize,client_socket,f)
                    self.send_new(("prepare to download " + filename+".md5").encode(), client_socket, f)
                    md5_head = self.recv_new(client_socket,f)
                    md5_filename = struct.unpack(self.fmt, md5_head)[0].decode().rstrip('\0')
                    md5_filesize = struct.unpack(self.fmt, md5_head)[1]
                    print("md5 filename:" + md5_filename + "\nmd5 filesize:" + str(md5_filesize))
                    self.download_file(md5_filename, md5_filesize, client_socket,f)
                    correct = self.check_md5_correct(filename)
                    if correct:
                        self.send_new(("download " + filename + "success").encode(), client_socket, f)
                    else:
                        self.send_new(("download " + filename + "success").encode(), client_socket, f)
                elif local_filesize!= filesize:
                    self.send_new(("prepare to download " + filename).encode(), client_socket, f)
                    self.download_file(filename, filesize, client_socket)
                    self.send_new(("prepare to download " + filename + ".md5").encode(), client_socket, f)
                    md5_head = self.recv_new(client_socket,f)
                    md5_filename = struct.unpack(self.fmt, md5_head)[0].decode().rstrip('\0')
                    md5_filesize = struct.unpack(self.fmt, md5_head)[1]
                    print("md5 filename:" + md5_filename + "\nmd5 filesize:" + str(md5_filesize))
                    self.download_file(md5_filename + ".md5", md5_filesize, client_socket)
                    correct = self.check_md5_correct(md5_filename)
                    print("correct",correct)
                    if correct:
                        self.send_new(("download " + filename + "success").encode(), client_socket, f)
                    else:
                        self.send_new(("download " + filename + "Failed").encode(), client_socket, f)
                else:
                    self.send_new(("dont't need to download" + filename).encode(), client_socket, f)
                    print(filename,"don't need to update")
            except:
                try:
                    client_socket, client_ip = self.tcp_server_socket.accept()
                    print("Client:", client_ip, "connected")
                    if self.encryption.upper() == "YES":
                        a = client_socket.recv(1024)

                        publicKeyPK, pubKeySha256 = pickle.loads(a)
                        if hashlib.sha256(publicKeyPK).hexdigest() != pubKeySha256:
                            raise AuthenticationError("secure key changed！")
                        else:
                            publicKey = pickle.loads(publicKeyPK)
                            print("public key accept")
                      
                        sym_key = Fernet.generate_key()
                        
                        en_sym_key = rsa.encrypt(pickle.dumps(sym_key), publicKey)
                        en_sym_key_sha256 = hashlib.sha256(en_sym_key).hexdigest()
                        print("sending secure key")
                        client_socket.send(pickle.dumps((en_sym_key, en_sym_key_sha256)))
                        f = Fernet(sym_key)
                except Exception as e:
                    print("connection Err:", e)


        client_socket.close()



if __name__== "__main__":
    Tcpip_server = Tcpip_Server(20001,"no")
    Tcpip_server.start()
    # for i in range(3):
    #     t = threading.Thread(target = Tcpip_server.start,args = ())
    #     t.start()



