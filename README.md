# pssc	
[![](https://img.shields.io/badge/maven-v3.2.2-brightgreen.svg)](http://mvnrepository.com/)

Parallel spatiotemporal spectral clustering algorithm. 

The paper ( Parallel Spatiotemporal Spectral Clustering With Massive Trajectory Data ) is published on ISPRS international conference and can be [viewed](http://adsabs.harvard.edu/abs/2017ISPAr42W7.1173G) and [downloaded](https://www.int-arch-photogramm-remote-sens-spatial-inf-sci.net/XLII-2-W7/1173/2017/isprs-archives-XLII-2-W7-1173-2017.pdf) online.

## Purpose
pssc is an algorithm that aims to mine knowledge from massive trajectory data effectively and efficiently, it expands the time dimension on traditional spectral cluster and speeds up the algorithm by multiple  acceleration solutions.

## Overview
* Use taxi trajectory data as sample data, you can use your own data.
* Use DTW (dynamic time warping) method to calculate spatiotemporal distance.
* Use a  load  balanced  multi-threading method to parallelize the algorithm.
* Use Lanczos method to accelerate the eigenvalue decomposition.


