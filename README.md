# GongchengYouhua
研究生工程优化课程作业
---
### 编写环境
1. Windows 7 x64旗舰版
2. jdk1.8.0-66
3. Intellij idea 15.01

### 题目

求f(x) = x^4+x^3-x^2+1的解
1. 使用成功-失败法找出[a,b]
2. 用黄金分割法找出x*，ε=10-2

###内容

成功-失败法流程


1、选定初始点a 和步长h;
2、计算并比较f(a)和f(a+h)；有前进(1)和后退(2)两种情况：                                                                                                                                                                                                                                                                                                                                                                                                                        （1）前进计算：若f(a)≥f(a+h)，则步长加倍，计算f(a+3h)。若f(a+h) ≤f(a+3h)，令 a1=a, a2=a+3h, 停止运算；否则将步长加倍，并重复上述运算。
（2）前进计算：若f(a) ＜ f(a+h)，则将步长改为－h/4。计算f(a－h), 若f(a－h) ≥ f(a)，令 a1=a－h, a2=a+h, 停止运算；否则将步长加倍，继续后退。

Java代码
```
    /*
    获取区间
     */
    public  double[] getRegionOfSearch(double a, double h){
        double a1 = a;
        double a2 = a + h;
        double f1 = getfx(a1);
        double f2 = getfx(a2);
        double[] region = new double[2];

        if (f2 < f1){ //如果f(a)>f(a+h),则步长加倍
            region = compareFxAdvance(a1,a2,h,f1,f2);
        }else{ //后退
            h = -(h/4);
            region = compareFxRetreat(a1,a2,h,f1,f2);
        }
        return region;
    }

    /*

    前进比较f1和f2
     */
    public double[] compareFxAdvance(double a1,double a2,double h,double f1,double f2){

        double[] region = new double[2];

        h = 2 * h;
        a2 = a2 + h;
        f1 = f2;
        f2 = getfx(a2);
        while (f2<f1)
        {
            a1 = a2 - h;
            h = 2 * h;
            a2 = a2 + h;
            f1 = f2;
            f2 = getfx(a2);
        }

        region[0] = a1;
        region[1] = a2;

        return region;
    }

    /*
    后退比较f1和f2
     */
    public double[] compareFxRetreat(double a1,double a2,double h,double f1,double f2){

        double[] region = new double[2];
        a1 = a1 + h;
        f2 = f1;
        f1 = getfx(a1);

        while(f2>=f1){
            a2 = a1 - h;
            h = h + h;
            a1 = a1 + h;
            f2 = f1;
            f1 = getfx(a1);
        }
        region[0] = a1;
        region[1] = a2;

        return region;
    }


    //f(x)的计算公式
    public double getfx(double x){
        double fx = 0;
        fx = Math.pow(x,4) + Math.pow(x,3) - Math.pow(x,2) + 1;
        return fx;
    }
```    
以上4个方法分别为getRgionOfSearch（）参数为起始点a和步长h，compareFxAdvance（）和compareFxRetreat分别实现了前进运算和后退运算，getfx（）为计算f（x）的结果。
在main方法中使用一下代码：
```
        Main m = new Main();
        double a = 1; //初始点a
        double h = 0.1; //步长h
        double[] cd = m.getRegionOfSearch(a,h);
        System.out.println("c:"+cd[0]);
        System.out.println("d:"+cd[1]);
```
来打印出计算出的区间（c,d）。

黄金分割法算法流程

按照黄金分割算法流程写Java代码如下：

```
/*
    黄金分割法求x*
     */
    public double getGoldenSectionRes(double a,double b,double epsilon){

        double res = 0;
        double x1 = a + 0.382*(b - a);
        double x2 = a + b - x1;
        double R = getfx(x1);
        double G = getfx(x2);

        while (true){
            if(R>G){
                a = x1;
                if (Math.abs((b-a)) < epsilon)
                {
                    res = (a+b)/2;
                    break;
                }else {
                    x1 = x2;
                    x2 = a + 0.618 * (b - a);
                    R = G;
                    G = getfx(x2);
                    continue;
                }
            }else {

                b = x2;
                if (Math.abs((b-a)) < epsilon){
                    res = (a + b)/2 ;
                    break;
                }else {
                    x2 = x1;
                    x1= a + 0.382 * (b - a);
                    G = R ;
                    R = getfx(x1);
                    continue;

                }
            }
        }
        return res;
    }
```
方法getGoldenSectionRes（）的参数分别为使用成功-失败法计算出的区间（c,d）的初始值和结束值，以及使用的ε的值，本程序中取10-2  。在main方法中进行调用，代码如下。
```
    public static void main(String[] args) {
        Main m = new Main();
        double a = 1; //初始点a
        double h = 0.1; //步长h
        double epsilon = 0.01;
        double[] cd = m.getRegionOfSearch(a,h);
        System.out.println("c:"+cd[0]);
        System.out.println("d:"+cd[1]);
        System.out.println("x* is:"+ m.getGoldenSectionRes(cd[0],cd[1],epsilon));
    }
```




