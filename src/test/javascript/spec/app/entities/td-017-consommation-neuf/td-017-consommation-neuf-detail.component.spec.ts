import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD017ConsommationNeufDetailComponent } from 'app/entities/td-017-consommation-neuf/td-017-consommation-neuf-detail.component';
import { TD017ConsommationNeuf } from 'app/shared/model/td-017-consommation-neuf.model';

describe('Component Tests', () => {
  describe('TD017ConsommationNeuf Management Detail Component', () => {
    let comp: TD017ConsommationNeufDetailComponent;
    let fixture: ComponentFixture<TD017ConsommationNeufDetailComponent>;
    const route = ({ data: of({ tD017ConsommationNeuf: new TD017ConsommationNeuf(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD017ConsommationNeufDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD017ConsommationNeufDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD017ConsommationNeufDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD017ConsommationNeuf on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD017ConsommationNeuf).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
