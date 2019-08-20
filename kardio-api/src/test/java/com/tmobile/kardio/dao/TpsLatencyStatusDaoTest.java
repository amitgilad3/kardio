/*******************************************************************************
 * Copyright 2019 T-Mobile, Inc. or its affiliates. All Rights Reserved.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.tmobile.kardio.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.tmobile.kardio.ComponentStatusApplication;
import com.tmobile.kardio.TestDaoService;
import com.tmobile.kardio.TestDataProvider;
import com.tmobile.kardio.bean.TpsLatency;
import com.tmobile.kardio.bean.TpsLatencyHistory;
import com.tmobile.kardio.db.entity.K8sTpsLatencyHistoryEntity;
import com.tmobile.kardio.db.entity.TpsServiceEntity;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ComponentStatusApplication.class })
public class TpsLatencyStatusDaoTest {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private TpsLatencyStatusDao tpsLatencyStatusDao;
	@Autowired
	private TpsLatencyDao tpsLatencyDao;

	@Autowired
	private TestDaoService testDaoService;

	@Test
	public void testGetTotalTpsLatency() throws ParseException {
		String envName = "gettotaltpslatency";
    	Session session = sessionFactory.openSession();
    	K8sTpsLatencyHistoryEntity he=testDaoService.createK8sTpsLatencyHistoryEntity(session, envName);
    	Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(new Date());
		yesterday.add(Calendar.DATE, -1);
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(new Date());
		tomorrow.add(Calendar.DATE, 1);
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(yesterday.getTime());
		String endDate = sdf.format(tomorrow.getTime());
		List<TpsLatencyHistory> tpsLatList=tpsLatencyStatusDao.getTotalTpsLatency(startDate, endDate, he.getEnvironment().getEnvironmentName(), ""+he.getComponent().getComponentId());
		TpsLatencyHistory tpsLatencyStatus = new TpsLatencyHistory();		
		tpsLatList.add(tpsLatencyStatus);
		
	}
	@Test
	public void testGetCurrentTpsLatency() throws ParseException{
		String envName = "getcuurenttpslatency";
    	Session session = sessionFactory.openSession();
    	TpsServiceEntity tse=testDaoService.createTpsServiceEntity(session, envName);
    	List<TpsLatency> getTotalTpsLatency=tpsLatencyDao.getCurrentTpsLatency(tse.getEnvironment().getEnvironmentName(), TestDataProvider.DEFAULT_PLATFORM);
	}
	
	@Test
	public void testGetTpsAndLatOfParent() throws ParseException{
		String envName = "gettpsandlatparent";
    	Session session = sessionFactory.openSession();
    	K8sTpsLatencyHistoryEntity he=testDaoService.createK8sTpsLatencyHistoryEntity(session, envName);
    	Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(new Date());
		yesterday.add(Calendar.DATE, -1);
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(new Date());
		tomorrow.add(Calendar.DATE, 1);
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(yesterday.getTime());
		String endDate = sdf.format(tomorrow.getTime());
		String platform="plarform";
		List<TpsLatencyHistory> tpsLatList=tpsLatencyDao.getTpsAndLatOfParent(startDate, endDate, he.getEnvironment().getEnvironmentName(), ""+he.getComponent().getComponentId(), platform);
	}
	
	@Test
	public void testGetTpsAndLatOfComponent() throws ParseException{
		String envName = "getpslatComp";
    	Session session = sessionFactory.openSession();
    	K8sTpsLatencyHistoryEntity he=testDaoService.createK8sTpsLatencyHistoryEntity(session, envName);
    	Calendar yesterday = Calendar.getInstance();
		yesterday.setTime(new Date());
		yesterday.add(Calendar.DATE, -1);
		
		Calendar tomorrow = Calendar.getInstance();
		tomorrow.setTime(new Date());
		tomorrow.add(Calendar.DATE, 1);
		
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String startDate = sdf.format(yesterday.getTime());
		String endDate = sdf.format(tomorrow.getTime());
		String platform="plarform";
		List<TpsLatencyHistory> tpsLatList=tpsLatencyDao.getTpsAndLatOfComponent(startDate, endDate, he.getEnvironment().getEnvironmentName(), ""+he.getComponent().getComponentId(), platform);
	}
	
	@Test
	public void testGetCurrentTpsAndLatency() throws ParseException{
		String envName="getcurrenttpaandlatency";
		Session session = sessionFactory.openSession();
		String platform="plarform";
		TpsServiceEntity tse=testDaoService.createTpsServiceEntity(session, envName);
		TpsLatency rettps=tpsLatencyDao.getCurrentTpsAndLatency(tse.getEnvironment().getEnvironmentName(), ""+tse.getComponent().getComponentId(), true, platform);
		//Assert.assertEquals("Size does not match", 1, rettps.size());
	}
}

