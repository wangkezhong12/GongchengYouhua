
public class Main {

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
}
