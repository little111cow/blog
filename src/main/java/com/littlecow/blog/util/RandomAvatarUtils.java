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
         "https://cdn.pixabay.com/photo/2022/04/20/01/23/wedding-7144049__480.jpg",
         "https://www.stockvault.net/data/2019/07/08/266697/thumb16.jpg",
         "https://www.stockvault.net/data/2019/11/11/270460/thumb16.jpg",
         "https://www.stockvault.net/data/2019/12/07/271457/thumb16.jpg",
         "https://www.stockvault.net/data/2019/07/17/266889/thumb16.jpg",
         "https://www.stockvault.net/data/2019/10/08/269954/thumb16.jpg",
         "https://www.stockvault.net/data/2019/11/14/270538/thumb16.jpg",
         "https://www.stockvault.net/data/2012/10/07/136244/thumb16.jpg",
         "https://www.stockvault.net/data/2020/01/02/272284/thumb16.jpg",
         "https://www.stockvault.net/data/2020/01/16/272556/thumb16.jpg",
         "https://www.stockvault.net/data/2019/12/01/270833/thumb16.jpg",
         "https://cdn6-banquan.ituchong.com/weili/sm/1068282534182912007.webp",
         "https://cdn3-banquan.ituchong.com/weili/sm/223609827288547339.webp",
         "https://cdn6-banquan.ituchong.com/weili/sm/544170930538283051.webp",
         "https://cdn3-banquan.ituchong.com/weili/sm/1132987247151808520.webp",
         "https://cdn3-banquan.ituchong.com/weili/sm/1120678068449968143.webp",
         "https://cdn3-banquan.ituchong.com/weili/sm/1113808697739968519.webp",
         "https://cdn6-banquan.ituchong.com/weili/sm/1089256646047039524.webp",
         "https://cdn3-banquan.ituchong.com/weili/sm/1089506295324475653.webp",
         "https://cdn9-banquan.ituchong.com/weili/sm/1154541635016327205.webp",
         "https://cdn6-banquan.ituchong.com/weili/sm/903261970645385327.webp",
         "https://cdn6-banquan.ituchong.com/weili/sm/903261970645385327.webp",
         "https://tse4-mm.cn.bing.net/th/id/OIP-C.VC96IQ53w1EhF5yR442lGAHaNK?w=115&h=189&c=7&r=0&o=5&pid=1.7",
         "https://tse4-mm.cn.bing.net/th/id/OIP-C.OLb-IYVPZn6oW3ENsMZKOQHaNK?w=115&h=189&c=7&r=0&o=5&pid=1.7",
          "https://tse3-mm.cn.bing.net/th/id/OIP-C.GGCyQfLOE0cT8lypS6enuQHaEu?w=296&h=189&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.1zMo-77F0KNEbklTzOledQHaEo?w=303&h=189&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.8olSEek1RZjSQDgPizhxEAHaJ3?w=143&h=189&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.gg1NSgs5KwP71Nh0qlvflQHaFL?w=269&h=187&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.nBxDzMs4xDelZogsHrhoBgHaLL?w=124&h=187&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.GGBG9mbliwLpOuEXqPJ9QQHaKe?w=132&h=187&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.JrnYeosaMJXO3Ba1njGtWwHaEK?w=332&h=187&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.4wrAqfAlqlefjBZwOG_ewQHaNK?w=115&h=187&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.jmp0yMKwSBZq4kxzMqKpqwHaLH?w=120&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.kt0IFgq0UDB27XX0jfTbmwHaE7?w=270&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.ghZDkWeTh8QmK43VdNR6bwHaE7?w=270&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.sz3vVmd4KML5mcNPqgwZqgHaEK?w=320&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.7yIXBDf0-S_CatlrO0FJVQHaLH?w=115&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.mwGmyqPSjM8BCTdtbFz7ogHaLH?w=115&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.DFLPaGx7bjlmfRpUXtghKgHaLH?w=115&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.pMgUBJa5n6ILo7V23a1k-QHaE8?w=255&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.kbKFR0DCTRvMp7UCEhsxuAHaHa?w=168&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.I9fx9m9HRalVdBtBXGrxUQHaGW?w=196&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.12haw9Z-hzR2L0w-zJXK9QHaKr?w=125&h=181&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.zvT30U4CsXwMrsE0EwTSRwHaE8?w=272&h=181&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.N4GblbUeWuIic90oyDGq3QHaHa?w=181&h=181&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.kzEliG9KSzzmP37BAsgYvwHaLH?w=121&h=181&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.rQaEpCVjbbUv3-g-6dKDsQHaE8?w=273&h=181&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.URaCrflCAK5OOFsD6dBPMAHaHa?w=196&h=196&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.Msz5Tj3UTgVXfPolWO3UfgHaE7?w=294&h=196&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C._gsxYK-7rGYIUeBW3qagQwHaLG?w=131&h=196&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.gvD6PRKvU0TQKj5U4VPcHQHaHa?w=195&h=196&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.Apvt56D4pPIQOH3Rbdi2mAHaJQ?w=156&h=196&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.dYOAHKrrT0zDtkR7jsJBkgHaE7?w=296&h=197&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.sF1z9VLFhSMdgWzwQGySWAHaKU?w=141&h=197&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.6g8YRQTTimhZ9-3q15pwaQHaF7?w=246&h=197&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.1829-RaJZf_UPxzQMCnIdQHaE8?w=297&h=197&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.VMzQrBUK7RZy3Cqr9bDYwgHaE8?w=244&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.k4QKVbRo49DGBob3Iw8-0wHaE8?w=244&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.UGejAGef_slxDCDU_9SJjgHaE2?w=248&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.Pk20h-R1IfibcxuU9PfAkQHaE8?w=244&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.1UuoI8Stnp2biEijm7xufAHaE_?w=232&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.qEL9qXWiEWaAeodBkDBFkAHaE8?w=235&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.tZUNMh7mJGb-TbQOTf8vQAHaEK?w=278&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.uhNFrZbIft0uEtvyb2qSlgHaE7?w=235&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.7RTJJG-fcuUZg_xuS7NqNgHaLH?w=127&h=190&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.bqd-QqPv7C2JIMnm7ijcXAHaE7?w=285&h=190&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.lNbR8AJ6fTVI_PAF5EJyigHaE7?w=284&h=190&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.0AI8cQWKhtCvgxUIal1T1wHaE7?w=284&h=190&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.ZwIpT1sB25kKotbexBQN1AHaHe?w=182&h=184&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.5f1U-O1fFGoWg9yd578b3QHaE8?w=276&h=184&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.6yx-XAiEct9ut3qcmiN98QHaFj?w=245&h=184&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.kk9EHtzqT5POydrpZlFvPgHaE5?w=277&h=184&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.RivBO75skvnct2E-U2j2-QHaE8?w=263&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.76i8d3lM-PTY932dru55IQHaEp?w=281&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.0DNBk3ERjd3VnqsxSdB0PQHaKi?w=123&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.uc5YPof_nkUkIJr2lxzBNQHaEK?w=313&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.-iPOpRQIo8fr5CuAiQRvfgHaJ4?w=154&h=206&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.1b-FnZX4ZSKtdanIaJaM4AHaKq?w=143&h=206&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.2eJB1EoikFX5vCW7IWWANwHaJo?w=158&h=206&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.SeeDC6Q9ethknA0YZJMJrQHaLH?w=137&h=206&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.BM4zPlBJPA2MbAsNwrjfJAHaIV?w=183&h=206&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.DxDAXM42hGalS0jERlwDlQHaIE?w=189&h=206&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.BtOzzMlgbABrJCViKLd-aQHaH8?w=154&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.cgUmEx9rraop_kQOHgnGWwHaFf?w=223&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.3a_iNjS7dLTFuC5PeZUxuwHaMc?w=115&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.gPy8ale1jolPn2RfqRYjRAHaKw?w=115&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.Iz1xr6FL15Ae0ON8LQLu6gHaIi?w=141&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.K1Wrh_e1WZmhInFo30fFFgHaFr?w=216&h=180&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.vJc5ajt_OaanTa-tBz8nDgHaJQ?w=176&h=220&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.tnORCQkoPZ8ydIffS0eC2wHaNK?w=128&h=220&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.aPxm6PFFfOQmZpF8L6LENQHaLH?w=151&h=220&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.hpJ9BtGkaAsQcTMx-u_-YgHaIB?w=207&h=220&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.QyZRukv_6n-7EJohIv2WBwHaLH?w=151&h=220&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.e6Nf_XsXBf0VuRNPWOChZwHaLG?w=151&h=220&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.BEz1u4lxpQT5nKRY9hMVFAHaKl?w=194&h=277&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.Z02cA00UFd_J0SMohLurawHaKe?w=194&h=274&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.ciXJvL7461Awl9NQSiBeMQHaHa?w=194&h=194&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.LKjqAUQ3BcEd73xUAkRrTAHaKF?w=194&h=264&c=7&r=0&o=5&pid=1.7",
        "https://tse2-mm.cn.bing.net/th/id/OIP-C.OFa_Cx74_H3wTynQEg3XWwHaI4?w=194&h=232&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.QP5ntlo3ho1VzXL95qyqbQHaGa?w=194&h=168&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.LiLy9hxrxJio9EoMljTZ_AHaIA?w=194&h=209&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.So3O2R23zlsYbZMcpc_KpwHaKe?w=194&h=274&c=7&r=0&o=5&pid=1.7",
        "https://tse3-mm.cn.bing.net/th/id/OIP-C.mtz_4cqaODTRInSqVBYP-gHaIF?w=194&h=212&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.QZbXehAq6LViYAYclnsqUAHaGQ?w=194&h=164&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.TtLdjQtSlZ184wSThe6PcAHaHa?w=194&h=194&c=7&r=0&o=5&pid=1.7",
        "https://tse1-mm.cn.bing.net/th/id/OIP-C.8-cl_SUmyE3hr8a73wXV9gHaKe?w=194&h=274&c=7&r=0&o=5&pid=1.7",
        "https://tse4-mm.cn.bing.net/th/id/OIP-C.cYhQ_p6AAjJfS89IGaajBQHaJc?w=194&h=248&c=7&r=0&o=5&pid=1.7"
        };
        Random rd = new Random();
        return baseAvatar[rd.nextInt(baseAvatar.length)];
    }
}
