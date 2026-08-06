package com.sakila.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.sakila.dao.AddressDAO;
import com.sakila.modal.Address;
import com.sakila.modal.City;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository("addressDAO")
public class AddressDAOImpl implements AddressDAO {

	@PersistenceContext
	private EntityManager entityManagerFactory;

	@Override
	public List<Address> getAddressDetails() {
		log.info("... Entered into getAddressDetails() of AddressDAOImpl ...");
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> criteria = builder.createQuery(Address.class);
		Root<Address> contactRoot = criteria.from(Address.class);
		criteria.select(contactRoot);
		Query<Address> query = session.createQuery(criteria);
		query.setFirstResult(0);
		query.setMaxResults(100);
		List<Address> addresses = query.getResultList();
		log.info("Address Size : {}", addresses.size());
		return addresses;
	}

	@Override
	public Address getAddressDetailsById(int addressId) {
		log.info("... Entered into getAddressDetails() of AddressDAOImpl ...");
		Session session = (Session) entityManagerFactory.getDelegate();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Address> criteriaQuery = builder.createQuery(Address.class);
		Root<Address> contactRoot = criteriaQuery.from(Address.class);
		criteriaQuery.select(contactRoot);
		criteriaQuery.where(builder.equal(contactRoot.get("addressId"), addressId));
		criteriaQuery.select(contactRoot);

		Query<Address> query = session.createQuery(criteriaQuery);
		Address address2 = query.getSingleResult();
		log.info("Address Id : {}", address2.getAddressId());
		log.info("Address : {}", address2.getAddress());
		log.info("Address2 : {}", address2.getAddress2());
		log.info("District : {}", address2.getDistrict());
		log.info("Phone : {}", address2.getPhone());
		log.info("Postal Code : {}", address2.getPostalCode());
		log.info("City Name : {}", address2.getCity().getCity());
		log.info("Last Update : {}", address2.getLastUpdate());

		Address address = new Address();
		address.setAddressId(address2.getAddressId());
		address.setAddress(address2.getAddress());
		address.setAddress2(address2.getAddress2());
		address.setDistrict(address2.getDistrict());
		address.setPhone(address2.getPhone());
		address.setPostalCode(address2.getPostalCode());

		try {
			StringBuffer sb = new StringBuffer();
			sb.append("C:").append(File.separator).append("Waste").append(File.separator).append("photo")
					.append(File.separator).append(address2.getAddressId());
			sb.toString();
			log.info(sb.toString());
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
			log.info(outputFileLocation + " File Created in Project root directory");
		}
		FileOutputStream outputStream = new FileOutputStream(file);
		outputStream.write(fileBytes);
		outputStream.close();
	}

}
