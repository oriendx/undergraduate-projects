import sys,getopt
from tcpip_server import Tcpip_Server
from tcpip_client import Tcpip_Client

import threading


def main(argv):

    try:
        opts,args = getopt.getopt(argv,"ip:encryption:",["ip=","encryption="])
    except getopt.GetoptError:
        print("python3 main.py --ip <ip_address> --encryption <encryption>")
        return
    encryption = "no"
    for opt,arg in opts:
        if opt == "--ip":
            ip_address = arg
        elif opt == "--encryption":
            encryption = arg

    ip_address_list = ip_address.split(",")
    Tcpip_server = Tcpip_Server(20001, encryption= encryption)

    for i in range(3):
        t = threading.Thread(target = Tcpip_server.start,args = ())
        t.start()
    
    port_start = 20010

    for ip in ip_address_list:
        Tcpip_client = Tcpip_Client("no")
        t = threading.Thread(target=Tcpip_client.sync_share, args=(ip, port_start))
        t.start()


if __name__ == "__main__":

    main(sys.argv[1:])
    #main(['--ip', '127.0.0.1',"--encryption","no"])
