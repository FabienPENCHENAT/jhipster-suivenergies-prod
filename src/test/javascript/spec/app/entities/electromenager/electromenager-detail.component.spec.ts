import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { ElectromenagerDetailComponent } from 'app/entities/electromenager/electromenager-detail.component';
import { Electromenager } from 'app/shared/model/electromenager.model';

describe('Component Tests', () => {
  describe('Electromenager Management Detail Component', () => {
    let comp: ElectromenagerDetailComponent;
    let fixture: ComponentFixture<ElectromenagerDetailComponent>;
    const route = ({ data: of({ electromenager: new Electromenager(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [ElectromenagerDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(ElectromenagerDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ElectromenagerDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load electromenager on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.electromenager).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
