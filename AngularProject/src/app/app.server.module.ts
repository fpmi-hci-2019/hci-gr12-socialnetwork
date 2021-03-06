import {NgModule} from '@angular/core';
import {ServerModule} from '@angular/platform-server';

import {AppModule} from './app.module';
import {ModuleMapLoaderModule} from '@nguniversal/module-map-ngfactory-loader';
import {RootComponent} from './root/root.component';

@NgModule({
  imports: [
    AppModule,
    ServerModule,
    ModuleMapLoaderModule,
  ],
  bootstrap: [RootComponent],
})
export class AppServerModule {
}
