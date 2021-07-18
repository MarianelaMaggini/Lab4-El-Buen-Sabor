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

// Login y Registro de usuario
import { LoginComponent } from './components/auth/login/login.component';
import { RegistroComponent } from './components/auth/registro/registro.component';

// Servicios
// Servicio de Articulo
import { ArticuloService } from './services/articulo.service';

// Servicio de Mercado Pago
import { MercadoPagoService } from './services/mercado-pago.service';


// Interceptor
import { interceptorProvider } from './interceptors/app-interceptor.service';

// Imports externos
// Alertas
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

// Angularx-social
import { SocialLoginModule, SocialAuthServiceConfig } from 'angularx-social-login';
import {
  GoogleLoginProvider,
  
} from 'angularx-social-login';
import { TipoEnvioComponent } from './components/tipo-envio/tipo-envio.component';
import { FormaPagoComponent } from './components/forma-pago/forma-pago.component';
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
    TipoEnvioComponent,
    FormaPagoComponent
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
  ],
  providers: [
    ArticuloService, 
    MercadoPagoService,
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
