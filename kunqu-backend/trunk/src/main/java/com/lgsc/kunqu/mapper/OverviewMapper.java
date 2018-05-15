package com.lgsc.kunqu.mapper;

import com.lgsc.kunqu.common.dao.BaseMapper;
import com.lgsc.kunqu.model.Overview;

public interface OverviewMapper extends BaseMapper<Overview> {
	
	/**
	 * 修改目录编号为空
	 * @param dirId
	 * @return
	 */
	public int updateDirIdNull(long dirId);
	
}