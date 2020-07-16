package com.codeman.smbms.service;

import java.util.List;

import com.codeman.smbms.entity.Bill;
import com.codeman.smbms.mapper.BillMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillServiceImpl implements BillService {
	@Autowired
	private BillMapper billMapper;

	@Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean add(Bill bill) {
		boolean result = false;
		if (billMapper.add(bill) > 0) {
			result = true;
		}
		return result;
	}

    @Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public List<Bill> getBillList(Bill bill) {
		return billMapper.getBillList(bill);
	}

    @Transactional(propagation = Propagation.REQUIRED)
	@Override
	public boolean deleteBillById(String delId) {
		boolean result = false;
		if (billMapper.deleteBillById(delId) > 0) {
			result = true;
		}
		return result;
	}

	@Override
    @Transactional(propagation = Propagation.SUPPORTS)
	public Bill getBillById(String id) {
		return billMapper.getBillById(id);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED)
	public boolean modify(Bill bill) {
		boolean result = false;
		if (billMapper.modify(bill) > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public int getBillCountByProviderId(String providerId) {
		return billMapper.getBillCountByProviderId(providerId);
	}

}
