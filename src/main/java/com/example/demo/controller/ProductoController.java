package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.DetallePedidoEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.entity.UsuarioEntity;
import com.example.demo.model.Pedido;
import com.example.demo.service.ProductoService;
import com.example.demo.service.UsuarioService;

@Controller
public class ProductoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ProductoService productoService;
	
	@GetMapping("/menu")
	public String showMenu(HttpSession session, Model model) {
		if(session.getAttribute("usuario") == null) {
			return "redirect:/";
		}
		
		String correo = session.getAttribute("usuario").toString();
		UsuarioEntity usuarioEntity = usuarioService.buscarUsuarioPorCorreo(correo);
		model.addAttribute("foto", usuarioEntity.getUrlImagen());
		
		List<ProductoEntity>productos = productoService.buscarTodosProductos();
		model.addAttribute("productos", productos);
		
		//numero pedido
		//List<Pedido> productoSession = session.getAttribute("carrito") == null ? new ArrayList<Pedido>() : (List<Pedido>) session.getAttribute("carrito");
		
		List<Pedido>productoSession = null;
		if(session.getAttribute("carrito") == null) {
			productoSession = new ArrayList<Pedido>();
		}else {
			productoSession = (List<Pedido>) session.getAttribute("carrito");
		}
		model.addAttribute("cant_carrito", productoSession.size());
		
		
		List<DetallePedidoEntity> detallePedidoEntityList = new ArrayList<DetallePedidoEntity>();
		for(Pedido pedido: productoSession) {
			DetallePedidoEntity detallePedidoEntity = new DetallePedidoEntity();
			//ProductoEntity productoEntit
		}
		
											
		return "menu";
	}
	
	@PostMapping("/agregar_producto")
	public String agregarProducto(HttpSession session,@RequestParam("prodId") String prod,
			@RequestParam("cant") String cant) {
		
		List<Pedido> productos = null;
		if(session.getAttribute("carrito") == null) {
			productos = new ArrayList<>();
		}else {
			productos = (List<Pedido>) session.getAttribute("carrito");
		}
		
		Integer cantidad = Integer.parseInt(cant);
		Integer prodId = Integer.parseInt(prod);
		Pedido pedido = new Pedido(cantidad, prodId);
		productos.add(pedido);
		session.setAttribute("carrito", productos);
		return "redirect:/menu";
		
	}

}
