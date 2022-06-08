#task1
# 导入所需库
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt
import seaborn as sns
#import用来导入其他python文件（成为模块module），导入后可以使用该模块里的类、方法或者变量，从而达到代码复用的目的
from sklearn.cluster import KMeans
from sklearn import preprocessing
#使用sklearn.cluster中的KMeans函数，下一行同理
#get_ipython().run_line_magic('matplotlib', 'inline')
#这行代码可以在你调用matplotlib.pyplot的绘图函数plot()时直接在python console生成图像
plt.style.use("ggplot")
plt.rcParams['font.sans-serif']=['SimHei']
plt.rcParams['axes.unicode_minus'] = False
#正常显示字体，不然可能会出现乱码（一堆小方块）


# In[36]:


# 导入数据，请将用户购买行为数据集csv文件下载到本地
data_train =pd.read_csv(r'E:\firefoxdownloads\E-Commerce-Visitor-Data.csv', encoding='gbk')#读取进来的数据命名为data_train
#使用encoding=utf-8可以使得pandas读入中文字符


# In[15]:


# 了解数据的基本情况
np.info(data_train)
print(data_train.head())
#data_train.head()会将excel表格第一行看作列名，并默认读取前五行


# In[18]:


#绘图，用柱状图完成访客类型的可视化
#设置画图的尺寸
visitor_type=data_train[['访客类型']]#visitor_type.sort_values(by='访客类型', inplace=True)
xx =np.unique(data_train[['月份']])#使用unqiue，去除重复元素，并按元素从大到小返回一个新的无元素的元组或列表
yy =pd.value_counts(data_train[['访客类型']])#使用value_counts，默认显示数字形式，默认排序，默认降序，默认删除NA
plt.bar(xx, yy, width=0.3, facecolor='#9999ff')#绘制条形图
a=np.arange(len())#np.arange()返回一个有终点和起点的固定步长的排列
for a,b in zip(a, yy):
    plt.text(a, b, '%.2f'%b, ha='center', va='bottom', fontsize=14)#设置数字标签
plt.xticks(xx,fontsize=12)
plt.yticks(yy,fontsize=12)
plt.title('按访客类型统计')#设置x、y轴标签与图形标题

plt.show()


# In[ ]:
#计算初次访客与回头客的购买率
group=data_train.groupby('访客类型')
#groupby()的作用是进行数据的分组以及分组后的组内运算
New_V=group.get_group('New_Visitor')
New_V_rate=len(New_V[New_V['是否购买']==1])/len(New_V)
#1为购买，0为未购买，用户购买行为数据集中有体现
Returning_V=group.get_group('Returning_Visitor')
Returning_V_rate=len(Returning_V[Returning_V['是否购买']==1])/len(Returning_V)
print('回头客的购买率:',Returning_V_rate)
print('初次访客的购买率:',New_V_rate)



# In[23]:


#计算工作日与周末的购买率,请参考计算初次访客和回头客的方法完成这部分的计算
group=data_train.groupby('周末')
weekends=group.get_group('TRUE')
weekends_rate=len(weekends[weekends['是否购买']==1])/len(weekends)
workdays=group.get_group('FALSE')
workdays_rate=len(workdays[workdays['是否购买']==1])/len(workdays)
print('周末的购买率:',weekends_rate)
print('工作日的购买率:',workdays_rate)



# In[27]:


#不同月份的访客量
plt.figure(figsize=(4,3))
#设置画图的尺寸
xx = ['Jan','Feb','Mar','Apr','May','June','Jul','Aug','Sep','Oct','Nov','Dec']
yy = []
for i in range(12):
    yy.append(len(data_train[data_train['月份']==xx[i]]))#append()在列表末尾添加一个元素
plt.bar(xx,yy,width=0.3,facecolor='#9999ff')#绘制条形图
a=np.arange(len(xx))
for a,b in zip(a,yy):
    plt.text(a,b,'%.2f'%b,ha='center',va='bottom',fontsize=14)
#设置数字标签
plt.xticks(xx,fontsize=12)
plt.yticks(yy,fontsize=12)
plt.title('不同月份访客量')
#设置x、y轴标签与图形标题
plt.show()


#不同月份的购买量
#请按照上部分内容自主完成
plt.figure(figsize=(4,3))
xx2=['Jan','Feb','Mar','Apr','May','June','Jul','Aug','Sep','Oct','Nov','Dec']
yy2=[]
for i in range(12):
    yy2.append(len(data_train[data_train['月份']==xx2[i]]))#append()在列表末尾添加一个元素
plt.bar(xx,yy,width=0.3,facecolor='#9999ff')#绘制条形图
a=np.arange(len(xx2))
plt.bar(xx2,yy2,width=0.3,facecolor='#9999ff')#设置条形图
b=np.arange(len(xx2))
for a,b in zip(a,yy2):
    plt.text(a,b,'%.2f'%b,ha='center',va='bottom',fontsize=14)
plt.xticks(xx2,fontsize=12)
plt.yticks(yy2,fontsize=12)
plt.title('不同月份购买量')

plt.show()


# In[29]:


#用分布图展示用户退出率和跳出率的分布情况
plt.figure(figsize=(4,3))#设置画图的尺寸
xx=data_train[['退出率']]
sns.distplot(xx, color='b', rug=True)
sns.distplot(xx, hist=False)#不显示直方图
plt.hist(xx)#plt.hist

xx2=data_train[['跳出率']]
sns.distplot(xx2, color='a', rug=True)
sns.distplot(xx2, hist=False)
plt.hist(xx2)

