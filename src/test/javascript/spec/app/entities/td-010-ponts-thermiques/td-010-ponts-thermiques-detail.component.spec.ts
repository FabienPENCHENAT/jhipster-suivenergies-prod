import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SuivEnergiesTestModule } from '../../../test.module';
import { TD010PontsThermiquesDetailComponent } from 'app/entities/td-010-ponts-thermiques/td-010-ponts-thermiques-detail.component';
import { TD010PontsThermiques } from 'app/shared/model/td-010-ponts-thermiques.model';

describe('Component Tests', () => {
  describe('TD010PontsThermiques Management Detail Component', () => {
    let comp: TD010PontsThermiquesDetailComponent;
    let fixture: ComponentFixture<TD010PontsThermiquesDetailComponent>;
    const route = ({ data: of({ tD010PontsThermiques: new TD010PontsThermiques(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SuivEnergiesTestModule],
        declarations: [TD010PontsThermiquesDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TD010PontsThermiquesDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TD010PontsThermiquesDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load tD010PontsThermiques on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.tD010PontsThermiques).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
