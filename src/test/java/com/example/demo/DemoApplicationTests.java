package com.example.demo;

import com.example.demo.dao.SysPermissionRepository;
import com.example.demo.entity.SysPermission;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

	@Autowired
	private SysPermissionRepository permissionRepository;

	@Test
	public void Test(){
		List<SysPermission> permissions = permissionRepository.findAll();
		System.out.println(permissions.size());
	}

}
