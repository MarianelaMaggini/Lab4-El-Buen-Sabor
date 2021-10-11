
// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  articuloUrl: 'http://localhost:8080/articulos',
  mpUrl: 'http://localhost:8080/createAndRedirect',
  authUrl: 'http://localhost:8080/auth/',
  tipoEnvioUrl: 'http://localhost:8080/tiposEnvio',
  domicilioUrl: 'http://localhost:8080/domicilios',
  pedidoUrl: 'http://localhost:8080/pedidos',
  pedidoEstadosUrl: 'http://localhost:8080/pedidoEstados',
  detallesPedidoUrl: 'http://localhost:8080/detallesPedido',
  localidadUrl: 'http://localhost:8080/localidades',
  articuloElaboradoDetallesUrl:'http://localhost:8080/articuloElaboradoDetalles',
  recetaElaboradoUrl:'http://localhost:8080/recetasElaborado',
  tiempoUrl: 'http://localhost:8080/tiempos',
  inventarioUrl: 'http://localhost:8080/inventarios',
  changePasswordUrl:'http://localhost:8080/account'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
