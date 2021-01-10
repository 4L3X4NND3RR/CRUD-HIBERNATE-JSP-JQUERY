<%-- 
    Document   : index
    Created on : Feb 4, 2020, 2:57:02 PM
    Author     : alexanderdeleon
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
        <link rel="stylesheet" href="resources/css/bootstrap.min.css"/>
        <script src="resources/jquery/jquery-3.4.1.min.js"></script>
        <script src="resources/ajax/Productos.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a href="#" class="navbar-brand">Producto</a>
            <ul class="navbar-nav ml-auto">
                <form class="form-inline my-2 my-lg-0">
                    <input type="search" id="txtBuscar" class="form-control mr-sm-2" placeholder="Buscar producto"/>
                    <input type="submit" class="btn btn-success my-2 my-sm-0" value="Buscar"/>
                </form>
            </ul>
        </nav>
        <div class="container p-4">
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-body">
                            <form id="frmProducto">
                                <div class="form-group">
                                    <input class="form-control" type="text" id="Codigo" placeholder="Codigo"/>
                                    <input class="form-control" type="text" id="Seccion" placeholder="Seccion"/>
                                    <input class="form-control" type="text" id="Nombre" placeholder="Nombre"/>
                                    <input class="form-control" type="number" id="Precio" placeholder="Precio" step="0.01" min="0" max="700"/>
                                    <input class="form-control" type="date" id="Fecha"/>
                                    <input type="radio" id="Si" name="Importado" value="true" />
                                    <label for="Si">Si</label>
                                    <input type="radio" id="No" name="Importado" value="false" checked="checked" />
                                    <label for="No">No</label>
                                    <input class="form-control" type="text" id="Origen" placeholder="Origen"/>
                                </div>
                                <input type="submit" class="btn btn-primary btn-block text-center" value="Guardar" />
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="card my-1">
                        <div class="card-body" id="resultadoproductos">
                            <ul id="container"></ul>
                        </div>
                    </div>
                    <table class="table table-bordered table-sm">
                        <th>Codigo</th>
                        <th>Seccion</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Fecha</th>
                        <th>Importado</th>
                        <th>Origen</th>
                        <tbody id="productos"></tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
