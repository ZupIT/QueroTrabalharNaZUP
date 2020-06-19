package br.com.zup.recruiting.ramonfacchinzip.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.recruiting.ramonfacchinzip.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, String> {

}
