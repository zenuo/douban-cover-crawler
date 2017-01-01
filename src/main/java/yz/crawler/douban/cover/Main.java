package yz.crawler.douban.cover;

/**
 * Created by yz on 12/31/16.
 */

public class Main {

    public static String IP;
    public static String USER;
    public static String PASSWD;
    public static String ISBN_FILE;
    public static int AMOUNT_OF_THREADS;

    public static void main(String[] args) {
        //读取参数
        try {
            if (args.length != 5) {
                System.out.println("Useage: java -jar crawler-1.0.jar DB_IP USER PASSWD ISBN_FILE AMOUNT_OF_THREADS");
                System.out.println("Please retry, now exit.");
                return;
            } else {
                IP = args[0];
                USER = args[1];
                PASSWD = args[2];
                ISBN_FILE = args[3];
                AMOUNT_OF_THREADS = Integer.valueOf(args[4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Please retry.");
            return;
        }
        //读取文件，返回二位数组
        String[][] strings = Util.getArrayOfISBN();
        //将数组分别分配给线程
        for (int i = 0; i < Main.AMOUNT_OF_THREADS; i++) {
            Worker worker = new Worker("T_" + String.valueOf(i + 1), strings[i]);
            worker.start();
        }
    }
}