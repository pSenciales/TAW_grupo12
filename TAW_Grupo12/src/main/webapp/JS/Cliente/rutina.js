$(document).ready(function() {
    $('.list-group-item').on('click', function() {
        const ejercicioId = $(this).data('id');
        const ejercicio = listaEjercicios.find(ej => ej.id == ejercicioId);

        if (ejercicio) {
            $('#ejercicio-detalle').html(`
                        <h5 class="card-title">${ejercicio.nombre}</h5>
                        <p><strong>Descripcion:</strong> ${ejercicio.descripcion}</p>
                        <p><strong>Repeticiones:</strong> ${ejercicio.repeticiones}</p>
                        <p><strong>Cantidad:</strong> ${ejercicio.peso}</p>
                        <form id="ejercicio-form-detalle">
                            <div class="form-group">
                                <label for="rendimiento-repeticiones">Número de repeticiones conseguidas:</label>
                                <input type="number" class="form-control" id="rendimiento-repeticiones" name="repeticiones">
                            </div>
                            <div class="form-group">
                                <label for="rendimiento-peso">Cantidad conseguida:</label>
                                <input type="number" class="form-control" id="rendimiento-peso" name="cantidad">
                            </div>
                        </form>
                        <div class="video-placeholder">
                            ${ejercicio.video}
                        </div>
                    `);

            $('#ejercicio-form-detalle').on('input', function() {
                const repeticiones = $('#rendimiento-repeticiones').val();
                const cantidad = $('#rendimiento-peso').val();

                $(`.list-group-item[data-id="${ejercicioId}"] .ejercicio-repeticiones`).val(repeticiones);
                $(`.list-group-item[data-id="${ejercicioId}"] .ejercicio-cantidad`).val(cantidad);
            });
        }
    });
});