import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD006BatimentDetailComponent } from 'app/entities/td-006-batiment/td-006-batiment-detail.component';
import { TD006Batiment } from 'app/shared/model/td-006-batiment.model';

describe('Component Tests', () => {
  describe('TD006Batiment Management Detail Component', () => {
    let comp: TD006BatimentDetailComponent;
    let fixture: ComponentFixture<TD006BatimentDetailComponent>;
    const route = ({ data: of({ tD006Batiment: new TD006Batiment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD006BatimentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD006BatimentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD006BatimentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD006Batiment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD006Batiment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
