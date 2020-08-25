import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD002ConsommationsComponent } from 'app/entities/td-002-consommations/td-002-consommations.component';
import { TD002ConsommationsService } from 'app/entities/td-002-consommations/td-002-consommations.service';
import { TD002Consommations } from 'app/shared/model/td-002-consommations.model';

describe('Component Tests', () => {
  describe('TD002Consommations Management Component', () => {
    let comp: TD002ConsommationsComponent;
    let fixture: ComponentFixture<TD002ConsommationsComponent>;
    let service: TD002ConsommationsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD002ConsommationsComponent],
      })
        .overrideTemplate(TD002ConsommationsComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD002ConsommationsComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD002ConsommationsService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD002Consommations(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD002Consommations && comp.tD002Consommations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
