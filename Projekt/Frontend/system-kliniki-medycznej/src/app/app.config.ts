import {ApplicationConfig, LOCALE_ID, provideZoneChangeDetection} from '@angular/core';
import {provideRouter, withComponentInputBinding} from '@angular/router';

import {routes} from './app.routes';
import {provideHttpClient, withInterceptors, withInterceptorsFromDi} from '@angular/common/http';
import {provideAnimationsAsync} from '@angular/platform-browser/animations/async';
import {registerLocaleData} from '@angular/common';
import localePl from '@angular/common/locales/pl';
import {provideNativeDateAdapter} from '@angular/material/core';
import {authInterceptor} from './features/auth/interceptor/auth.interceptor';

registerLocaleData(localePl, 'pl');
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({eventCoalescing: true}),
    provideRouter(routes, withComponentInputBinding()),
    provideHttpClient(), provideAnimationsAsync(),
    {
      provide: LOCALE_ID,
      useValue: 'pl'
    },
    provideNativeDateAdapter()
  ]
};
