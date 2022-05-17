package com.littlecow.blog.util;

import java.util.Random;

public class RandomAvatarUtils {
    public static String RandPicture(){
        String[] baseAvatar= new String[]{
        "http://5b0988e595225.cdn.sohucs.com/images/20181103/feaa7d14883047fb81bbaa16f681f583.jpeg",
        "http://n.sinaimg.cn/sinacn20111/600/w1920h1080/20190331/0f41-huxwryw5226043.jpg",
         "http://p.qpic.cn/dnfbbspic/0/dnfbbs_dnfbbs_dnf_gamebbs_qq_com_forum_202002_04_082156ifotspmtuzcffycn.jpg/0",
         "https://pic.quanjing.com/19/x5/QJ9106651790.jpg?x-oss-process=style/show_794s",
         "/files/avatar.jpg",
         "https://scpic.chinaz.net/files/pic/pic9/201910/zzpic20739.jpg",
         "https://www.keaidian.com/uploads/allimg/190424/24110307_19.jpg",
         "https://scpic.chinaz.net/files/pic/pic9/202009/apic27858.jpg",
         "https://tse2-mm.cn.bing.net/th/id/OIP-C.PTBpJTBaZPJZ1MBV-2UTAAHaKD?w=117&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
         "https://tse2-mm.cn.bing.net/th/id/OIP-C.P3NSGTdAYdyqy5zJpb5QXQHaEo?w=254&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          "https://tse4-mm.cn.bing.net/th/id/OIP-C.yQ1yIE-UJ1bBtC-1vOsTYQHaGI?w=212&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
          "https://tse3-mm.cn.bing.net/th/id/OIP-C.cT1RA85bYHiJ0erDaDQpiwHaFj?w=235&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7",
         "https://cdn.pixabay.com/photo/2022/02/21/06/26/insect-7025881__340.jpg",
         "https://cdn.pixabay.com/photo/2020/07/14/14/37/background-5404414__340.jpg",
         "https://cdn.pixabay.com/photo/2022/05/07/20/22/flowers-7180947__340.jpg",
         "https://cdn.pixabay.com/photo/2022/05/11/23/51/river-7190415__340.jpg",
         "https://cdn.pixabay.com/photo/2022/05/13/14/35/sea-7193894__340.jpg",
         "https://cdn.pixabay.com/photo/2022/05/08/12/51/flower-7182055__340.jpg",
         "https://cdn.pixabay.com/photo/2022/02/13/12/07/seagull-7011013__480.jpg",
         "https://cdn.pixabay.com/photo/2022/04/20/01/23/wedding-7144049__480.jpg"
        };
        Random rd = new Random();
        return baseAvatar[rd.nextInt(baseAvatar.length)];
    }
}
