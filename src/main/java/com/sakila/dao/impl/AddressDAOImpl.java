package com.sakila.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.sakila.dao.AddressDAO;
import com.sakila.modal.Address;
import com.sakila.modal.City;

@Repository("addressDAO")
public class AddressDAOImpl implements AddressDAO {

	private static final Logger loger = LoggerFactory.getLogger(AddressDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManagerFactory;

	@Override
	public List<Address> getAddressDetails() {
		loger.info("... Entered into getAddressDetails() of AddressDAOImpl ...");
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		Root<Address> contactRoot = criteria.from(Address.class);
		criteria.select(contactRoot);
		Query<Address> query = session.createQuery(criteria);
		query.setFirstResult(0);
		query.setMaxResults(100);
		List<Address> addresses = query.getResultList();
		System.out.println("Address Size : " + addresses.size());
		return addresses;
	}

	@Override
	public Address getAddressDetailsById(int addressId) {
		loger.info("... Entered into getAddressDetails() of AddressDAOImpl ...");
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> criteriaQuery = builder.createQuery(Address.class);
		Root<Address> contactRoot = criteriaQuery.from(Address.class);
		criteriaQuery.select(contactRoot);
		criteriaQuery.where(builder.equal(contactRoot.get("addressId"), addressId));
		criteriaQuery.select(contactRoot);

		Query<Address> query = session.createQuery(criteriaQuery);
		Address address2 = query.getSingleResult();
		System.out.println("Address Id : " + address2.getAddressId());
		System.out.println("Address : " + address2.getAddress());
		System.out.println("Address2 : " + address2.getAddress2());
		System.out.println("District : " + address2.getDistrict());
		System.out.println("Phone : " + address2.getPhone());
		System.out.println("Postal Code : " + address2.getPostalCode());
		System.out.println("City Name : " + address2.getCity().getCity());
		System.out.println("Last Update : " + address2.getLastUpdate());

		Address address = new Address();
		address.setAddressId(address2.getAddressId());
		address.setAddress(address2.getAddress());
		address.setAddress2(address2.getAddress2());
		address.setDistrict(address2.getDistrict());
		address.setPhone(address2.getPhone());
		address.setPostalCode(address2.getPostalCode());

		try {
			StringBuffer sb = new StringBuffer();
			sb.append("C:").append(File.separator).append("Waste").append(File.separator).append("photo").append(File.separator).append(address2.getAddressId());
			sb.toString();
			System.out.println(sb.toString());
			String photoFilePathToSave = "C:\\\\HK\\\\Waste\\\\photo\\\\";
//			String photoFilePathToSave = sb.toString();
			Blob blob = address2.getLocation();
			byte[] blobBytes = blob.getBytes(1, (int) blob.length());
			saveBytesToFile(photoFilePathToSave, blobBytes);
			blob.free();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		City city = new City();
		city.setCityId(address2.getCity().getCityId());
		city.setCity(address2.getCity().getCity());
		city.setLastUpdate(address2.getCity().getLastUpdate());
		address.setCity(city);

		address.setLastUpdate(address2.getLastUpdate());

		return address;
	}

	private void saveBytesToFile(String outputFileLocation, byte[] fileBytes) throws IOException {
		File file = new File(outputFileLocation, "kiran.png");
		if (file.createNewFile()) {
			System.out.println(outputFileLocation + " File Created in Project root directory");
		}
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(fileBytes);
		outputStream.close();
	}

}
