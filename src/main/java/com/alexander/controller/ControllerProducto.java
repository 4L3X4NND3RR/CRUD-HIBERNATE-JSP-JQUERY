package com.alexander.controller;

import com.alexander.entities.Producto;
import com.alexander.model.ModelProducto;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author alexanderdeleon
 */
@WebServlet(name = "ControllerProducto", urlPatterns = { "/ControllerProducto" })
public class ControllerProducto extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private SessionFactory sessionFactory;
	private ModelProducto modeloProducto;
	private Gson gson;
	private SimpleDateFormat format;

	@Override
	public void init() throws ServletException {
		super.init();
		try {
			Configuration configuration = new Configuration();
			configuration.addAnnotatedClass(Producto.class);
			configuration.configure();
			ServiceRegistry service = new StandardServiceRegistryBuilder()
					.applySettings(configuration.getProperties()).build();
			sessionFactory = configuration.buildSessionFactory(service);
			modeloProducto = new ModelProducto(sessionFactory);
			format = new SimpleDateFormat("yyyy-MM-dd");
			gson = new Gson();
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String instruccion = request.getParameter("instruccion");
		if (instruccion == null) {
			instruccion = "inicio";
		}
		switch (instruccion) {
		case "inicio":
			inicio(request, response);
			break;
		case "listar":
			listarProductos(request, response);
			break;
		default:
			inicio(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String instruccion = request.getParameter("instruccion");
		if (instruccion == null) {
			instruccion = "inicio";
		}
		switch (instruccion) {
		case "inicio":
			inicio(request, response);
			break;
		case "agregar":
			agregarProducto(request, response);
			break;
		case "editar":
			editarProducto(request, response);
			break;
		case "producto":
			obtenerProducto(request, response);
			break;
		default:
			inicio(request, response);
		}
	}

	private void listarProductos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		List<Producto> productos = modeloProducto.getProductos(0, 9);
		String jsonProductos = gson.toJson(productos);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonProductos);
		out.flush();
	}

	private void inicio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("vistas/index.jsp");
		dispatcher.forward(request, response);
	}

	private void agregarProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String codigo = request.getParameter("codigo");
		String seccion = request.getParameter("seccion");
		String nombre = request.getParameter("nombre");
		float precio = Float.parseFloat(request.getParameter("precio"));
		Date fecha = null;
		try {
			fecha = format.parse(request.getParameter("fecha"));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		boolean importado = Boolean.parseBoolean(request.getParameter("importado"));
		String origen = request.getParameter("origen");
		Producto producto = new Producto(codigo, seccion, nombre, precio, fecha, importado, origen);
		modeloProducto.saveProducto(producto);
	}

	private void editarProducto(HttpServletRequest request, HttpServletResponse response) {
		String codigo = request.getParameter("codigo");
		String seccion = request.getParameter("seccion");
		String nombre = request.getParameter("nombre");
		float precio = Float.parseFloat(request.getParameter("precio"));
		Date fecha = null;
		try {
			fecha = format.parse(request.getParameter("fecha"));
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		boolean importado = Boolean.parseBoolean(request.getParameter("importado"));
		String origen = request.getParameter("origen");
		Producto producto = new Producto(codigo, seccion, nombre, precio, fecha, importado, origen);
		System.out.println(producto.toString());
		modeloProducto.updateProducto(producto);
		System.out.println("Todo bien\n" + producto.toString());
	}

	private void obtenerProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String codigo = request.getParameter("codigo");
		Producto producto = modeloProducto.getProducto(codigo);
		String jsonProducto = gson.toJson(producto);
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		out.print(jsonProducto);
		out.flush();
	}
}
