package com.psl.stock.backend.services;

import java.util.List;
import java.util.Optional;

// import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.psl.stock.backend.entities.Logistic_ReceivedBy;
// import com.psl.stock.backend.entities.AddFromEntity;
// import com.psl.stock.backend.repositories.LogisticsRepo;
import com.psl.stock.backend.repositories.REceivedByRepo;
@Service
public class ReceivedByService {
    @Autowired
    private REceivedByRepo repo;
    public List<Logistic_ReceivedBy> findAllReceivedBy(){
        return this.repo.findAll();
    }
    public Logistic_ReceivedBy insertReceivedBy(Logistic_ReceivedBy receivedBy) {
        // return this.repo.save(logistic_ReceivedBy);
      return  this.repo.save(receivedBy);
    }
    public Optional<Logistic_ReceivedBy> findByName(String name){
        return this.repo.findByReceiver(name);
    }
    public List<Logistic_ReceivedBy> deleteData(int id){
         this.repo.deleteById(id);
       return  this.repo.findAll();
    }
    public List<Logistic_ReceivedBy> findAll(){
        //  this.repo.deleteById(id);
       return  this.repo.findAll();
    }
}
