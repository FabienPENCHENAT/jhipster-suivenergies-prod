import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD017ConsommationNeufComponent } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf.component';
import { TD017ConsommationNeufService } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf.service';
import { TD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';

describe('Component Tests', () => {
  describe('TD017ConsommationNeuf Management Component', () => {
    let comp: TD017ConsommationNeufComponent;
    let fixture: ComponentFixture<TD017ConsommationNeufComponent>;
    let service: TD017ConsommationNeufService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD017ConsommationNeufComponent],
      })
        .overrideTemplate(TD017ConsommationNeufComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD017ConsommationNeufComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD017ConsommationNeufService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD017ConsommationNeuf(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD017ConsommationNeufs && comp.tD017ConsommationNeufs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
