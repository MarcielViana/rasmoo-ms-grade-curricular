package com.rasmoo.cliente.escola.gradecurricular.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.rasmoo.cliente.escola.gradecurricular.exception.MateriaException;
import com.rasmoo.cliente.escola.gradecurricular.repository.MateriaRepository;

@Service
public class MateriaServiceImpl implements MateriaService {
	
	@Autowired
	private MateriaRepository materiaRepository;

	@Override
	public Boolean atualizar(MateriaEntity materia) {
		try {
			Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(materia.getId());
			
			if(materiaOptional.isPresent()) {
				MateriaEntity materiaEntityAtualizada = materiaOptional.get();
				
				//atualizamos todos os valores
				materiaEntityAtualizada.setNome(materia.getNome());
				materiaEntityAtualizada.setCodigo(materia.getCodigo());
				materiaEntityAtualizada.setHoras(materia.getHoras());
				materiaEntityAtualizada.setNome(materia.getNome());
				materiaEntityAtualizada.setFrequencia(materia.getFrequencia());
				
				//salvamos as alteracoes
				this.materiaRepository.save(materiaEntityAtualizada);
				
				return true;
			}
			return false;
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean excluir(Long id) {
		try {
			this.materiaRepository.deleteById(id);
			return true;
			
		}catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public MateriaEntity consultar(Long id) {
		try {
			Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);
			if (materiaOptional.isPresent()) {
				return materiaOptional.get();
			}
			throw new MateriaException("Matéria não encontrada", HttpStatus.NOT_FOUND);
		}catch (MateriaException m) {
			throw m;
		}catch (Exception e) {
			throw new MateriaException("Erro interno identificado. Contate o suporte", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<MateriaEntity> listar() {
		try {
			return this.materiaRepository.findAll();
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public Boolean cadastrar(MateriaEntity materia) {
		try {
			this.materiaRepository.save(materia);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
