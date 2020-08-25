import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD002ConsommationsDetailComponent } from 'app/entities/td-002-consommations/td-002-consommations-detail.component';
import { TD002Consommations } from 'app/shared/model/td-002-consommations.model';

describe('Component Tests', () => {
  describe('TD002Consommations Management Detail Component', () => {
    let comp: TD002ConsommationsDetailComponent;
    let fixture: ComponentFixture<TD002ConsommationsDetailComponent>;
    const route = ({ data: of({ tD002Consommations: new TD002Consommations(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD002ConsommationsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD002ConsommationsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD002ConsommationsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD002Consommations on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD002Consommations).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
