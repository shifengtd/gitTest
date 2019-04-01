package cn.hxzy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.hxzy.dao.ItemsDao;
import cn.hxzy.pojo.ItemsCustom;
import cn.hxzy.pojo.ItemsQueryVo;
import cn.hxzy.service.ItemsService;


@Service
public class ItemsServiceImpl implements ItemsService {

	@Autowired
	ItemsDao itemsDao;

	/**
	 * 查询商品条目
	 */
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {

		return itemsDao.findItemsList(itemsQueryVo);
	}

	public ItemsCustom findItemsById(Integer id) throws Exception {

		ItemsCustom itemsCustom = itemsDao.findItemsById(id);

		return itemsCustom;
	}

	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {

		itemsCustom.setId(id);
		itemsDao.updateItems(id, itemsCustom);
	}

}
