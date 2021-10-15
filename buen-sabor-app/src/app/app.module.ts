import { NgModule, LOCALE_ID, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { AppComponent } from './app.component';
import { BrowserModule } from '@angular/platform-browser';

// Locale 
import localeEs from '@angular/common/locales/ar';
import { registerLocaleData } from '@angular/common';

// Peticiones HTTP
import { HttpClientModule } from '@angular/common/http';

// Formularios
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// Rutas
import { AppRoutingModule } from './app-routing.module';

// Componentes genéricos
import { CartComponent } from './shared/cart/cart.component';
import { FooterComponent } from './shared/footer/footer.component';
import { HeaderComponent } from './shared/header/header.component';
import { ItemCartComponent } from './shared/item-cart/item-cart.component';

// Servicios
import { ArticuloElaboradoDetalleService } from './services/articulo-elaborado-detalle.service';
import { ArticuloService } from './services/articulo.service';
import { AuthService } from './services/auth.service';
import { DomicilioService } from './services/domicilio.service';
import { EmailPasswordService } from './services/email-password.service';
import { interceptorProvider } from './interceptors/app-interceptor.service';
import { LocalidadService } from './services/localidad.service';
import { MessageService } from './services/message.service';
import { MercadoPagoService } from './services/mercado-pago.service';
import { PedidoEstadoService } from './services/pedidoEstado.service';
import { PedidoService } from './services/pedido.service';
import { RecetaElaboradoService } from './services/receta-elaborado.service';
import { StorageService } from './services/storage.service';
import { TipoEnvioService } from './services/tipo-envio.service';
import { TokenService } from './services/token.service';

// Imports externos
// Alertas
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

// Angularx-social
import { SocialLoginModule, SocialAuthServiceConfig } from 'angularx-social-login';
import { GoogleLoginProvider } from 'angularx-social-login';

// Sweet Alert 2
import { SweetAlert2Module } from '@sweetalert2/ngx-sweetalert2';

// Spinner
import { NgxSpinnerModule } from 'ngx-spinner';

// Modulos propios
import { AuthModule } from './modules/auth/auth.module';
import { ArticulosModule } from './modules/articulos/articulos.module';
import { PedidosModule } from './modules/pedidos/pedidos.module';
import { CheckoutModule } from './modules/checkout/checkout.module';
import { PerfilModule } from './modules/perfil/perfil.module';

registerLocaleData(localeEs);
@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    CartComponent,
    ItemCartComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule, // required animations module
    ReactiveFormsModule,
    AuthModule,
    ArticulosModule,
    PedidosModule,
    CheckoutModule,
    PerfilModule,
    SocialLoginModule,
    ToastrModule.forRoot(), // ToastrModule added
    SweetAlert2Module.forRoot(),
    NgxSpinnerModule,
  ],
  schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [
    {provide: LOCALE_ID, useValue: 'ar-AR'},
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
    EmailPasswordService,
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
