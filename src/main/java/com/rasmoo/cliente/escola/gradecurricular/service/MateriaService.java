package com.rasmoo.cliente.escola.gradecurricular.service;

import java.util.List;

import com.rasmoo.cliente.escola.gradecurricular.entity.MateriaEntity;

public interface MateriaService {
	
	public Boolean atualizar(final MateriaEntity materia);
	
	public Boolean excluir(final Long id);
	
	public List<MateriaEntity> listar();
	
	public MateriaEntity consultar(final Long id);
	
	public Boolean cadastrar(final MateriaEntity materia);

}
