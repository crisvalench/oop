package com.ups.oop.service;

import com.ups.oop.dto.LoanDetailDTO;
import com.ups.oop.entity.Loan;
import com.ups.oop.entity.LoanDetail;
import com.ups.oop.repository.LoanDetailRepository;
import com.ups.oop.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanDetailService {

    private final LoanDetailRepository loanDetailRepository;

    public LoanDetailService(LoanDetailRepository loanDetailRepository) {
        this.loanDetailRepository = loanDetailRepository;
    }

    public List<LoanDetailDTO> getLoanDetail(){
        Iterable<LoanDetail> loanDetailIterable = loanDetailRepository.findAll();
        List<LoanDetailDTO> loanDetailList = new ArrayList<>();
        for(LoanDetail ld : loanDetailIterable){
            LoanDetailDTO loanDetailDTO = new LoanDetailDTO();
            loanDetailDTO.setLoanSerial(ld.getLoan().getSerial());
            loanDetailDTO.setLoanClient(ld.getLoan().getClient().getName() + "-" + ld.getLoan().getClient().getLastname());
            loanDetailDTO.setLoanDate(ld.getLoan().getLoanDate().toString());
            loanDetailDTO.setBookName(ld.getBook().getTitle());
            loanDetailDTO.setAuthor(ld.getBook().getAuthor().getName() + "-" +ld.getBook().getAuthor().getLastName());
            loanDetailList.add(loanDetailDTO);
        }
        return loanDetailList;
    }
}
