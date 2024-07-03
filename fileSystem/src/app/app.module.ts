import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './pages/header/header.component';
import { UtilisateurComponent } from './pages/utilisateur/utilisateur.component';
import { IndexComponent } from './pages/index/index.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { FormsModule ,FormBuilder, FormGroup, Validators  } from '@angular/forms';
import { FilesComponent } from './pages/files/files.component';
 import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
 import { AuthGuard } from './auth/auth.guard';
import { AuthInterceptor } from './auth/auth.intercepter';
import { AdminService } from './monService/admin.service';
import { LoginComponent } from './pages/login/login.component';
import { ReactiveFormsModule } from '@angular/forms';
 import { FileArchiverComponent } from './pages/file-archiver/file-archiver.component';
import { RegisterComponent } from './pages/register/register.component';
import {MatFormFieldModule} from '@angular/material/form-field';
import { ListAutorisationComponent } from './pages/list-autorisation/list-autorisation.component';
  import { MatDialogModule } from '@angular/material/dialog';
import { ChatComponent } from './pages/chat/chat.component';
import { HighchartsChartModule } from 'highcharts-angular'; // Import HighchartsChartModule

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    UtilisateurComponent,
    IndexComponent,
    FilesComponent,
    
    LoginComponent,
     
    FileArchiverComponent,
    RegisterComponent,
    ListAutorisationComponent,
    ChatComponent,
     ],
  imports: [
    HighchartsChartModule ,
    BrowserModule,FormsModule,MatIconModule,ReactiveFormsModule,MatFormFieldModule,MatDialogModule,
    AppRoutingModule,HttpClientModule,MatButtonModule, BrowserAnimationsModule
  ],
  providers: [  AuthGuard,{
    provide:HTTP_INTERCEPTORS,
    useClass:AuthInterceptor,
    multi:true 
  
    
  },
  AdminService],
  bootstrap: [AppComponent]
})
export class AppModule { }
