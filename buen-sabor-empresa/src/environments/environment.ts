// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  rubroUrl: 'http://localhost:8080/rubros',
  tipoArticuloUrl: 'http://localhost:8080/tipoArticulos',
  articuloUrl: "http://localhost:8080/articulos",
  aedUrl: "http://localhost:8080/articuloElaboradoDetalles",
  recetaUrl: "http://localhost:8080/recetasElaborado",
  historicoArticuloUrl: "http://localhost:8080/historicoArticulos",
  inventarioUrl: "http://localhost:8080/inventarios",
  pedidoUrl: "http://localhost:8080/pedidos",
  pedidoEstadoUrl: "http://localhost:8080/pedidoEstados",
  detallePedidoUrl: "http://localhost:8080/detallesPedido",
  facturaUrl: "http://localhost:8080/facturas",
  detalleFacturaUrl: "http://localhost:8080/detallesFactura",
  tipoEnvioUrl: "http://localhost:8080/tiposEnvio",
  unidadMedidaUrl: "http://localhost:8080/unidadesMedida",
  fileUrl: "http://localhost:8080/upload"
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
