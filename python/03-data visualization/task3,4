import pandas as pd
fin_data=pd.read_csv(r'E:\firefoxdownloads\stock_data.csv',index_col='时间',encoding='gbk')
#请将填空的部分改为stock_data文件在你电脑中的位置，如果不知道怎么看位置的话，右击stock_data文件点击”属性“就可以看到
#在这里，index_col是使得时间作为dataframe的index（即索引），以方便之后的分析
#encoding=gbk是为了使得系统兼容汉字，否则输出的表格会是乱码的状态（如果表格内容都是英文的话就不需要了）
print(fin_data.head(10))
'''
E:\anaconda\python.exe "E:/Liam Kyo/cs/python workshop/Task-3/导入金融数据集.py"
               五粮液   泸州老窖     大盘趋势
时间
2020/1/2    129.88  83.86  474.052
2019/12/31  130.81  85.09  465.812
2019/12/30  130.62  84.14  462.588
2019/12/27  126.80  80.12  455.745
2019/12/26  125.95  79.14  457.606
2019/12/25  125.90  79.27  453.948
2019/12/24  126.50  80.46  452.892
2019/12/23  125.94  80.02  449.175
2019/12/20  126.90  80.56  456.071
2019/12/19  127.66  80.41  457.479

Process finished with exit code 0
'''

import pandas as pd, numpy as np, matplotlib.pyplot as plt

fin_data=pd.read_csv(r'E:\firefoxdownloads\stock_data.csv',index_col='时间',encoding='gbk')
#请将填空的部分改为stock_data文件在你电脑中的位置，如果不知道怎么看位置的话，右击stock_data文件点击”属性“就可以看到
#在这里，index_col是使得时间作为dataframe的index（即索引），以方便之后的分析
#encoding=gbk是为了使得系统兼容汉字，否则输出的表格会是乱码的状态（如果表格内容都是英文的话就不需要了）
print(fin_data.head(10))


#接下来我们利用Numpy计算一些股票数据的关键信息
#我们先计算五粮液的收盘价平均值
WLY_mean=np.mean(fin_data['五粮液'].values)#在使用numpy之前需要将dataframe的列转化为矩阵，直接.values即可~
print(WLY_mean)
#接下来我们计算五粮液收盘价的标准差
WLY_std=np.std(fin_data['五粮液'].values)
print(WLY_std)
#计算个股与大盘的相关系数
WLY_corr=np.corrcoef(fin_data['五粮液'].values,fin_data['大盘趋势'])
print(WLY_corr)

#测试：接下来请你计算泸州老窖的收盘价平均值、标准差、与相关系数
LZLJ_mean=np.mean(fin_data['泸州老窖'].values)
LZLJ_std=np.std(fin_data['泸州老窖'].values)
LZLJ_corr=np.corrcoef(fin_data['泸州老窖'].values,fin_data['大盘趋势'])
print(LZLJ_mean)
print(LZLJ_std)
print(LZLJ_corr)

#下面我们开始介绍股票数据的可视化,首先我们示例五粮液的股票数据
plt.Figure()
plt.title('A股酒类股票走势图-五粮液', fontsize=22)
plt.plot(fin_data['五粮液']) # 查看Open的走势
plt.xlabel('日期', fontsize = 22)
plt.ylabel('价格', fontsize = 22)
plt.Figure()
plt.title('A股酒类股票走势图-泸州老窖', fontsize=22)
plt.plot(fin_data['泸州老窖']) #
plt.xlabel('日期', fontsize = 22)
plt.ylabel('价格', fontsize = 22)
plt.Figure()
plt.title('A股酒类股票大盘走势图', fontsize=22)
plt.plot(fin_data['大盘趋势'])
plt.xlabel('日期', fontsize = 22)
plt.ylabel('价格', fontsize = 22)
plt.show()
#接下来请你分别绘制出泸州老窖的走势图、大盘的走势图、最后在一张图表中展示三张图(多个plot用一个plt.show()即可)


