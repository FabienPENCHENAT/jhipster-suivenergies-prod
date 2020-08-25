import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD010PontsThermiquesComponent } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques.component';
import { TD010PontsThermiquesService } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques.service';
import { TD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';

describe('Component Tests', () => {
  describe('TD010PontsThermiques Management Component', () => {
    let comp: TD010PontsThermiquesComponent;
    let fixture: ComponentFixture<TD010PontsThermiquesComponent>;
    let service: TD010PontsThermiquesService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD010PontsThermiquesComponent],
      })
        .overrideTemplate(TD010PontsThermiquesComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD010PontsThermiquesComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD010PontsThermiquesService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD010PontsThermiques(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD010PontsThermiques && comp.tD010PontsThermiques[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
