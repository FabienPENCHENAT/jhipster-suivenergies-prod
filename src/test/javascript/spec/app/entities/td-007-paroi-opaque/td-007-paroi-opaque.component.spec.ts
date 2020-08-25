import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD007ParoiOpaqueComponent } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque.component';
import { TD007ParoiOpaqueService } from 'app/entities/td-007-paroi-opaque/td-007-paroi-opaque.service';
import { TD007ParoiOpaque } from 'app/shared/model/td-007-paroi-opaque.model';

describe('Component Tests', () => {
  describe('TD007ParoiOpaque Management Component', () => {
    let comp: TD007ParoiOpaqueComponent;
    let fixture: ComponentFixture<TD007ParoiOpaqueComponent>;
    let service: TD007ParoiOpaqueService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD007ParoiOpaqueComponent],
      })
        .overrideTemplate(TD007ParoiOpaqueComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD007ParoiOpaqueComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD007ParoiOpaqueService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD007ParoiOpaque(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD007ParoiOpaques && comp.tD007ParoiOpaques[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
