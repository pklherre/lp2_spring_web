package com.example.demo.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.UsuarioEntity;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;


@Service
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@Override
	public void crearUsuario(UsuarioEntity usuarioEntity, Model model, MultipartFile foto) {
		usuarioRepository.save(usuarioEntity);
		
	}

	@Override
	public boolean validarUsuario(UsuarioEntity usuarioEntity, HttpSession session) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UsuarioEntity buscarUsuarioPorCorreo(String correo) {
		// TODO Auto-generated method stub
		return null;
	}

}
