import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD015ProductionEnergiesDetailComponent } from 'app/entities/td-015-production-energies/td-015-production-energies-detail.component';
import { TD015ProductionEnergies } from 'app/shared/model/td-015-production-energies.model';

describe('Component Tests', () => {
  describe('TD015ProductionEnergies Management Detail Component', () => {
    let comp: TD015ProductionEnergiesDetailComponent;
    let fixture: ComponentFixture<TD015ProductionEnergiesDetailComponent>;
    const route = ({ data: of({ tD015ProductionEnergies: new TD015ProductionEnergies(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD015ProductionEnergiesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD015ProductionEnergiesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD015ProductionEnergiesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD015ProductionEnergies on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD015ProductionEnergies).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
