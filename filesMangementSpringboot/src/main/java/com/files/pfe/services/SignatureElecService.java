package com.files.pfe.services;

import java.util.List;

import com.files.pfe.model.SignatureElec;

public interface SignatureElecService  {
	List<SignatureElec> listSignatureElec();
	
	SignatureElec addNewUtilisateur(SignatureElec signatureElec);
	
	SignatureElec findBYiDSignatureElec(Long id);
	
	void deleteByIdUtilisateur(Long id);
}
