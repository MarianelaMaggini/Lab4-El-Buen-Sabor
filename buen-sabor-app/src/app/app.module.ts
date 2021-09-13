import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';

// Peticiones HTTP
import { HttpClientModule } from '@angular/common/http';

// Formularios
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Rutas
import { AppRoutingModule } from './app-routing.module';

// Header
import { HeaderComponent } from './components/header/header.component';

// Footer
import { FooterComponent } from './components/footer/footer.component';

// Home
import { HomeComponent } from './components/home/home.component';

// Carrito
import { CartComponent } from './components/cart/cart.component';

// Detalles del Carrito
import { ItemCartComponent } from './components/item-cart/item-cart.component';

// Articulos
import { ArticulosComponent } from './components/articulos/articulos.component';

// Item Articulo
import { ItemArticuloComponent } from './components/item-articulo/item-articulo.component';

// Articulo Elaborado Detalle
import { ArticuloDetalleComponent } from './components/articulo-detalle/articulo-detalle.component';

// Login y Registro de usuario
import { LoginComponent } from './components/auth/login/login.component';
import { RegistroComponent } from './components/auth/registro/registro.component';

// Pedidos
import { PedidosComponent } from './components/pedidos/pedidos.component';

// Perfil
import { PerfilComponent } from './components/perfil/perfil.component';

// Servicios
// Servicio de Articulo
import { ArticuloService } from './services/articulo.service';

// Servicio de Mercado Pago
import { MercadoPagoService } from './services/mercado-pago.service';

// Servicio de Autenticación
import { AuthService } from './services/auth.service';

// Servicio de emisión y captura de mensaje
import { MessageService } from './services/message.service';

// Servicio de Pedido Estado
import { PedidoEstadoService } from './services/pedidoEstado.service';

// Servicio de Storage
import { StorageService } from './services/storage.service';

// Servicio de Token
import { TokenService } from './services/token.service';

// Servicio de Localidad
import { LocalidadService } from './services/localidad.service';

// Servicio de Receta Elaborado
import { RecetaElaboradoService } from './services/receta-elaborado.service';

// Servicio de Articulo Elaborado Detalle
import { ArticuloElaboradoDetalleService } from './services/articulo-elaborado-detalle.service';

// Servicio de Domicilio
import { DomicilioService } from './services/domicilio.service';

// Servicio de Pedido
import { PedidoService } from './services/pedido.service';
// Interceptor
import { interceptorProvider } from './interceptors/app-interceptor.service';

// Imports externos
// Alertas
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

// Angularx-social
import { SocialLoginModule, SocialAuthServiceConfig } from 'angularx-social-login';
import { GoogleLoginProvider } from 'angularx-social-login';

import { TipoEnvioService } from './services/tipo-envio.service';

// Sweet Alert 2
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    CartComponent,
    ItemCartComponent,
    ArticulosComponent,
    ItemArticuloComponent,
    LoginComponent,
    RegistroComponent,
    PedidosComponent,
    PerfilComponent,
    ArticuloDetalleComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    SocialLoginModule,
    ReactiveFormsModule,
    BrowserAnimationsModule, // required animations module
    ToastrModule.forRoot(), // ToastrModule added
    SweetAlert2Module.forRoot(),
  ],
  providers: [
    ArticuloService, 
    MercadoPagoService,
    TipoEnvioService,
    DomicilioService,
    PedidoService,
    AuthService,
    MessageService,
    PedidoEstadoService,
    StorageService,
    TokenService,
    LocalidadService,
    RecetaElaboradoService,
    ArticuloElaboradoDetalleService,
    interceptorProvider,
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '861209226872-2csq32a3rgkat7d3euic9aoevqumjrte.apps.googleusercontent.com'
            )
          },
        ]
      } as SocialAuthServiceConfig,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
