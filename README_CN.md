# pssc	
[![](https://img.shields.io/badge/maven-v3.2.2-brightgreen.svg)](http://mvnrepository.com/)

一种并行化的时空谱聚类算法

该论文（Parallel Spatiotemporal Spectral Clustering With Massive Trajectory Data）发表在ISPRS会议上，我是GIS（地理信息系统）方向的学生，该会议也是GIS和遥感为主的会议。论文可以通过互联网[浏览](http://adsabs.harvard.edu/abs/2017ISPAr42W7.1173G)和[下载](https://www.int-arch-photogramm-remote-sens-spatial-inf-sci.net/XLII-2-W7/1173/2017/isprs-archives-XLII-2-W7-1173-2017.pdf)。

论文有幸获得了会议的最佳poster，有幸上了一次武大[新闻](http://news.whu.edu.cn/info/1002/49526.htm)首页（本次会议在武汉举行，下一届在迪拜）。
## 目的
pssc算法旨在提出一种高效的轨迹数据挖掘方法，能够对海量真实轨迹数据（实验中为出租车轨迹）进行分析。该方法从时空维度上对传统的谱聚类算法进行了扩展，同时用并行等方法对整个算法进行了加速。

## 概览
* 利用出租车轨迹数据作为实验数据，您也可以使用自己的数据。
* 利用DTW（动态时间规整）方法来对谱聚类进行扩展。
* 利用多线程方法对算法的各个过程进行并行化。
* 利用Lanczos方法对特征值求解过程进行加速。

## 数据
样例数据存放在src/main/resources，该数据仅仅是真实数据（武汉）的很小一部分，包括以下字段：

| Field            | Type       | Description               |
| ---------------- |:----------:| -------------------------:|
| CAR_ID           | string     | taxi id                   |
| TIME_STAMP       | string     | time stamp                |
| LONGITUDE        | float      | car longitude             |
| LATITUDE         | float      | car latitude              |
| DIRECTION        | int        | car direction             |
| SPEED            | int        | car speed                 |
| CAR_STATUS       | string     | Carrying passenger or not |

你也可以使用自己的数据。

## 示例
示例1中展示的是对从武汉市的武昌火车站出发，目的地为关谷广场的所有出租车轨迹数据聚类的结果。
![](https://github.com/gyzag/pssc/raw/master/img/Demo1.png)

示例2中展示的是对从武汉市的武昌火车站出发，目的地为武汉火车站的所有出租车轨迹数据聚类的结果。
![](https://github.com/gyzag/pssc/raw/master/img/Demo2.png)
