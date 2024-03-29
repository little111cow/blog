package com.littlecow.blog;

import com.littlecow.blog.controller.BlogsController;
import com.littlecow.blog.service.BlogsService;
import com.littlecow.blog.service.UserLoginService;
import com.littlecow.blog.util.DownloadImageUtils;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
@Ignore
class BlogApplicationTests {
	@Resource
	UserLoginService userLoginService;

	@Resource
	BlogsService blogsService;

	@Resource
	BlogsController blogsController;

	@Test
	void contextLoads() {
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("user.home"));
	}

	@Test
    void TestSendMail(){
//	    String verifyCode = LoginCodeUtils.getRandomCode(6);
//		MailUtils.sendMail("whu_jianliu@163.com","验证码",
//                "您的验证码：" + verifyCode + "，如非本人操作，请忽略！请勿回复此邮箱");
	}

	@Test
	public void getImageUrlsTest(){
		System.out.println(DownloadImageUtils.getImageUrlsByKeyWord("女人"));
        System.out.println(DownloadImageUtils.getRandomImageFromInternet());
	}

	@Test
	void TestUserLoginService(){
//		User user =userLoginService.checkUserByUsernameAndPassword("Admin","1234567");
//		System.out.println(user);
//        System.out.println(System.getProperty("user.dir"));
    }

//	@Test
//	void TestBlogAdd(){
//		Blog blog = new Blog();
//		blog.setTitle("测试");
//		blog.setCreateTime(new Date());
//		blog.setTagIds("1,2");
//		blog.setTypeId(1L);
//		blog.setContent("这是正文");
//		blog.setDescription("博客描述");
//		blog.setAppreciation(true);
//		blog.setShareStatement(false);
//		blog.setCommentabled(false);
//		blog.setPublished(true);
//		blog.setRecommend(false);
//		System.out.println(blogsService.addBlog(blog));
//	}

//	@Test
//	void TestUpdateBlog(){
//		Blog blog = blogsService.getBlogById(1L);
//		blog.setTitle("maven相关");
//		System.out.println(blogsService.updateBlog(blog));
//	}

//	@Test
//	void TestSearchByCondition(){
//		Blog blog = new Blog();
////		blog.setTitle("哈");
////		blog.setPublished(true);
//		blog.setRecommend(true);
//		blog.setTypeId(3L);
//		System.out.println(blogsService.getBlogByCondition(blog));
//	}

//	@Test
//	void TestSearchByConditionController(){
//		Blog blog = new Blog();
////		blog.setTitle("哈");
////		blog.setPublished(true);
//		blog.setRecommend(true);
//		blog.setTypeId(3L);
//		PageHelper.startPage(1,Contants.PAGE_SIZE);
//		List<Blog> blogList = blogsService.getBlogByCondition(blog);
//		PageInfo<Blog> pageInfo = new PageInfo<>(blogList);
//		System.out.println(pageInfo);
//	}
}
