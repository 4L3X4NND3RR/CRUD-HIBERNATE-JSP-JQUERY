$(function () {
    $('#resultadoproductos').hide();
    let editando = false;
    let url = 'ControllerProducto';
    getProductos();

    function getProductos() {
        $.get(url, {instruccion: 'listar'}, function (response) {
            let productos = response;
            let template = '';
            productos.forEach(producto => {
                template += `
                    <tr CodigoEnviar="${producto.codigo}">
                        <td>${producto.codigo}</td>
                        <td>${producto.seccion}</td>
                        <td>
                            <a href="#" class="item-producto">${producto.nombre}</a>
                        </td>
                        <td>${producto.precio}</td>
                        <td>${producto.fecha}</td>
                        <td>${producto.importado}</td>
                        <td>${producto.origen}</td>
                        <td>
                            <button class="task-delete btn btn-danger">Eliminar</button>
                        </td>
                    </tr>
                    `;
            });
            $('#productos').html(template);
        });
    }

    $('#frmProducto').submit(function (e) {
        e.preventDefault();
        let instruccion = editando === false ? 'agregar' : 'editar';
        let datos = {
            instruccion: instruccion,
            codigo: $('#Codigo').val(),
            seccion: $('#Seccion').val(),
            nombre: $('#Nombre').val(),
            precio: $('#Precio').val(),
            fecha: $('#Fecha').val(),
            importado: $('#Importado').val(),
            origen: $('#Origen').val()
        };

        $.post(url, datos, function () {
            editando = false;
            $('#frmProducto').trigger('reset');
        });
    });

    $(document).on('click', '.item-producto', function () {
        let element = $(this)[0].parentElement.parentElement;
        let codigo = $(element).attr('CodigoEnviar');
        $.post(url, {instruccion: 'producto', codigo: codigo}, function (response) {
            console.log(response.fecha);
            $('#Codigo').val(response.codigo);
            $('#Seccion').val(response.seccion);
            $('#Nombre').val(response.nombre);
            $('#Precio').val(response.precio);
            var fecha = new Date(response.fecha);
            var day = ("0" + fecha.getDate()).slice(-2);
            var month = ("0" + (fecha.getMonth() + 1)).slice(-2);
            var fechaFormateada = fecha.getFullYear() + "-" + (month) + "-" + (day);
            $('#Fecha').val(fechaFormateada);
            if (response.importado == true) {
                $("#Si").prop("checked", true);
                $('#No').prop("checked", false);
            } else {
                $("#Si").prop("checked", false);
                $('#No').prop("checked", true);
            }
            $('#Origen').val(response.origen);
            editando = true;
        });
    });
});