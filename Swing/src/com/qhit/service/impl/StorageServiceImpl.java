package com.qhit.service.impl;

import java.util.List;

import com.qhit.dao.IStorageDao;
import com.qhit.dao.impl.StorageDaoImpl;
import com.qhit.model.Storage;
import com.qhit.model.StorageInfo;
import com.qhit.service.IStorageService;

public class StorageServiceImpl implements IStorageService {

	private IStorageDao storageDao = new StorageDaoImpl();

	@Override
	public boolean save(Storage storage) {
		return this.storageDao.save(storage);
	}

	@Override
	public List<StorageInfo> searchByInfo() {
		return this.storageDao.searchByInfo();
	}

	@Override
	public List<Storage> searchAll(String typeId, String startTime, String endTime) {
		return this.storageDao.searchAll(typeId, startTime, endTime);
	}

	@Override
	public List<Storage> getByAll(String typeId, String startNum, String endNum) {
		return this.storageDao.getByAll(typeId, startNum, endNum);
	}
}