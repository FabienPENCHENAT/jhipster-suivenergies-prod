import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD008BaieDetailComponent } from 'app/entities/td-008-baie/td-008-baie-detail.component';
import { TD008Baie } from 'app/shared/model/td-008-baie.model';

describe('Component Tests', () => {
  describe('TD008Baie Management Detail Component', () => {
    let comp: TD008BaieDetailComponent;
    let fixture: ComponentFixture<TD008BaieDetailComponent>;
    const route = ({ data: of({ tD008Baie: new TD008Baie(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD008BaieDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD008BaieDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD008BaieDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD008Baie on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD008Baie).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
