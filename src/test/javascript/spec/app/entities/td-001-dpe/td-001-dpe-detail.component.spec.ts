import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD001DPEDetailComponent } from 'app/entities/td-001-dpe/td-001-dpe-detail.component';
import { TD001DPE } from 'app/shared/model/td-001-dpe.model';

describe('Component Tests', () => {
  describe('TD001DPE Management Detail Component', () => {
    let comp: TD001DPEDetailComponent;
    let fixture: ComponentFixture<TD001DPEDetailComponent>;
    const route = ({ data: of({ tD001DPE: new TD001DPE(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD001DPEDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD001DPEDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD001DPEDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD001DPE on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD001DPE).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
