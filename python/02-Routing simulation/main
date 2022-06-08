import socket,time
import json
import operator
import sys
import binascii
import struct
import threading
import configparser


def is_diff(obj1,obj2):#Check for updates
    if obj1 == None or obj2 == None:
        return False
    for k in obj1[list(obj1.keys())[0]]:
        if obj1[list(obj1.keys())[0]][k] != obj2[list(obj2.keys())[0]][k]:
            return True
    return False

def print_table(obj):#Nice print info

    if obj == None:
        print(">>>>>> table is empty <<<<<<<<")
        return
    print('>>>> ' + list(obj.keys())[0] + ' routing table <<<<')
    dest_nodes = obj[list(obj.keys())[0]].keys()
    print('-------------------------------------------------------')
    print('|   destination   |    link cost    |    next hop     |')
    for dest_node in dest_nodes:
        dest = dest_node
        cost = obj[list(obj.keys())[0]][dest_node]["distance"]
        next_hop = obj[list(obj.keys())[0]][dest_node]["next_hop"]
        print('|    %-13s|    %-13s|    %-13s|' % (dest, cost, next_hop))
    print('-------------------------------------------------------')

def print_diff(obj1,obj2):
    if obj1 == None or obj2 == None:
        return
    if is_diff(obj1,obj2):
        print("Before Update Table")
        print_table(obj1)
        print("After Update Table")
        print_table(obj2)

        for k in obj1[list(obj1.keys())[0]]:
            if obj2[list(obj1.keys())[0]][k]["distance"] != obj1[list(obj1.keys())[0]][k]["distance"]:
                print("Node " + k + " distance changed from " + str(obj1[list(obj1.keys())[0]][k]["distance"]) + " to " + str(obj2[list(obj1.keys())[0]][k]["distance"]))
    else:
        print("Node " + list(obj1.keys())[0] + " routing table not changed")

def save_table(table):#Maintain node_config, which is used to check for updates
    global node_configs
    node_configs[list(table.keys())[0]] = table

class RecvThread(threading.Thread):
    #Listening threads
    def __init__(self,ip,port):
        super(RecvThread, self).__init__()
        self.port = port
        self.ip = ip

    def run(self):
        global node_configs
        s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        print(self.ip,self.port)
        s.bind((self.ip, self.port))
        while True:
            data, addr = s.recvfrom(1024)
            print("Recv from : ",addr);
            data_dict = json.loads(str(data,encoding='utf-8'))
            print_table(data_dict)
            name = list(data_dict.keys())[0]
            old = None
            if name in node_configs:
                old = node_configs[name]
            save_table(data_dict)
            print_diff(old,data_dict)
            Bellman_update(data_dict)


def Bellman_update(obj):
    #The central idea of Bellman processing mechanism: if the information 
    # saved by the current node to each node is greater than the information 
    # received from each node plus the distance value, then slacken and update 
    # distance and next hop
    change = False
    name1 = list(node_distance_dict.keys())[0]
    name2 = list(obj.keys())[0]

    for j in node_distance_dict[name1]:
        if j == name2:
            link=name1
            cost = int(node_distance_dict[name1][j]["distance"])
            break

    for k in obj[name2]:
        link_name = k
        for i in node_distance_dict[name1]:
            test_name = i
            if test_name == link_name:
                a = int(node_distance_dict[name1][i]["distance"])
                b = int(obj[name2][k]["distance"]) + cost
                if a > b:#鏉惧紱
                    node_distance_dict[name1][i]["distance"] = b
                    node_distance_dict[name1][i]["next_hop"] = name2
                    change = True
    if change:#If the current node information is changed, it is broadcast to other directly connected nodes
        print(node_distance_dict)
        send(node_distance_dict,node)




def send(node_distance_dict,node):
    #Broadcast information about the current node constructed to the directly connected nodes
    global s
    for to_node in distance_dict.keys():
        to_node_base_info = "./test_data/"+to_node+"_ip.json" #鑾峰彇to_node 鐨刬p鍜宲ort
        with open(to_node_base_info,"r") as f:
            to_node_info_dict = json.load(f)
        to_node_ip = to_node_info_dict[to_node][0]
        to_node_port = to_node_info_dict[to_node][1]

        s.sendto(bytes(json.dumps(node_distance_dict),'utf8'),(to_node_ip,int(to_node_port)))#udp鍙戦€?
    print("Send config finished")


#################entrance########################
if len(sys.argv) != 3:    #Determining the correctness of the input format
    print("Useage: python " + sys.argv[0] + "--node <node>")
    sys.exit(-1)
node = sys.argv[2]
distance_info = "./test_data/"+node+"_distance.json"
node_base_info = "./test_data/"+node+"_ip.json"
with open(node_base_info,"r") as f:
    config_dict = json.load(f)
with open(distance_info,"r") as f:
    distance_dict = json.load(f)
node_distance_dict = {node:distance_dict}
listen_port = int(config_dict[node][1])
listen_ip = config_dict[node][0]
#run recv thread
t = RecvThread(listen_ip,int(listen_port))#udp listener thread, used to constantly listen for information from other nodes
t.setDaemon(True)
t.start()

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)  #Main threads
nodes = ["u","v","w","x"]
node_configs = {}
with open(distance_info, "r") as f:
    distance_dict = json.load(f)  #Convert json file to dict
for k in distance_dict:#Remove the part of the distance file that goes from yourself to yourself, e.g. w_distance.json, w to w
    if k == node:
        distance_dict.pop(node)
        break
for node_t in nodes:
    #Redefine the udp transfer information, including the 
    # source node, to_node's distance and next hop. In addition, if the distance 
    # to a node is not defined in the json file, i.e., it cannot be reached directly, 
    # then distance is assigned to 10000 and next hop is the source node
    if (node_t in distance_dict) and node_t != node:
        distance_dict[node_t] = {"distance":distance_dict[node_t],"next_hop":node_t}

    elif (node_t not in distance_dict) and node_t !=node:
        distance_dict[node_t] = {"distance": 10000, "next_hop": node}

node_distance_dict = {node: distance_dict}#Include the source node as well
send(node_distance_dict,node) #Broadcast node information
while True:
    dict_obj = json.dumps(node_distance_dict[node],indent=4) #Constantly update the output file
    fileObject = open("./result/"+node+"_output.json","w")
    fileObject.write(dict_obj)
    fileObject.close()
    time.sleep(10)
