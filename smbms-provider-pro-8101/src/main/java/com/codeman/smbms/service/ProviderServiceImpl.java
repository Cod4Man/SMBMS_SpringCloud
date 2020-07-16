package com.codeman.smbms.service;

import java.util.List;

import com.codeman.smbms.entity.Provider;
import com.codeman.smbms.mapper.ProviderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProviderServiceImpl implements ProviderService {
	@Autowired
	private ProviderMapper providerMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean add(Provider provider) {
		boolean result = false;
		if (providerMapper.add(provider) > 0 ) {
			result = true;
		}
		return result;
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Provider> getProviderList(String proName, String proCode) {
		return providerMapper.getProviderList(proName, proCode);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public int deleteProviderById(String delId) {
		return providerMapper.deleteProviderById(delId);
	}

	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Provider getProviderById(String id) {
		return providerMapper.getProviderById(id);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean modify(Provider provider) {
		boolean result = false;
		if (providerMapper.modify(provider) > 0 ) {
			result = true;
		}
		return result;
	}

}
