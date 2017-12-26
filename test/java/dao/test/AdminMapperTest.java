package dao.test;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import service.IDisplayService;
import dao.AdminMapper;
import dao.BarrageMapper;
import dao.UserMapper;
import dao.UsercollectionMapper;
import dao.VideocommentMapper;
import entity.Admin;
import entity.Usercollection;
import entity.Videocomment;
import entity.Video;
import junit.framework.TestCase;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/springmvc-servlet.xml"})
public class AdminMapperTest extends TestCase {

	@Autowired
	public BarrageMapper barMapper;
	
	public VideocommentMapper videocommentMapper;
	
	public UserMapper userMapper;

	public UserMapper getUserMapper() {
		return userMapper;
	}
	@Resource
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	private IDisplayService displayService;

	public IDisplayService getDisplayService() {
		return displayService;
	}

	@Resource
	public void setIDisplayService(IDisplayService displayService) {
		this.displayService = displayService;
	}
	
	@Before
	public void setUp() throws Exception {
		super.setUp();
	}
/*	@Test
	public void testInsert() {
		System.out.println(11);
		Admin admin=new Admin("15605903692","admin");
		adminMapper.insert(admin);
	}
*/
/*	@Test
	public void testSelectByPrimaryKey() {
		adminMapper.deleteByPrimaryKey("15605903692");
	}
*/
	
	@Test
	public void testSelectByPrimaryKey() {
		System.out.println(barMapper.selectByExample(null));
		//System.out.println(userMapper.updateByPrimaryKeySelective("13063015677", "888",  "888",  "888",  "888",  "888",  "888",  "888",  "888"));	
		/*List<Video> vis=displayService.TypeTopLimit("3");
		for(Video vi:vis){
		System.out.println(vi.getVideoid());
		}*/
		/*System.out.println(displayService.TopPlayByType());*/
	}
}
