import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD006BatimentComponent } from 'app/entities/td-006-batiment/td-006-batiment.component';
import { TD006BatimentService } from 'app/entities/td-006-batiment/td-006-batiment.service';
import { TD006Batiment } from 'app/shared/model/td-006-batiment.model';

describe('Component Tests', () => {
  describe('TD006Batiment Management Component', () => {
    let comp: TD006BatimentComponent;
    let fixture: ComponentFixture<TD006BatimentComponent>;
    let service: TD006BatimentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD006BatimentComponent],
      })
        .overrideTemplate(TD006BatimentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(TD006BatimentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(TD006BatimentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new TD006Batiment(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.tD006Batiments && comp.tD006Batiments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
